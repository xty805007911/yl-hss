package com.ctsi.service;

import com.ctsi.entity.TbOrder;
import com.ctsi.entity.TbUser;
import com.ctsi.util.PageResult;
import com.ctsi.vo.GroupCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @Description:协同过滤算法
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/2/3  9:18
 */
@Service
public class SimilarityService {

    @Autowired
    private TbUserService userService;
    @Autowired
    private TbOrderService orderService;

    //获取推荐的用户
    public List<TbUser> getRecommendCare(Integer currentUserId) {
        List<TbUser> careList = new ArrayList<>();

        List<TbOrder> orderList = this.recommend(currentUserId);

        for(TbOrder order : orderList) {
            Integer careId = order.getCareId();
            TbUser care = userService.selectUserById(careId);
            careList.add(care);
        }

        if(careList.size() <= 5) {
            List<GroupCountVO> groupCountVOList = orderService.selectTopOrderCare();
            for(int i = 0; i<groupCountVOList.size()&& !CollectionUtils.isEmpty(groupCountVOList); i++) {
                Integer addCareId = groupCountVOList.get(i).getCareId();
                //如果推荐的为空，则直接添加进去
                if(CollectionUtils.isEmpty(careList)) {
                    careList.add(userService.selectUserById(addCareId));
                }
                //如果推荐的不为空，需提前判断推荐列表是否有此用户
                for(int j = 0; j<careList.size()&& !CollectionUtils.isEmpty(careList); j++) {
                    if(addCareId == careList.get(j).getId()) {
                        continue;
                    }else {
                        careList.add(userService.selectUserById(addCareId));
                    }
                }// for j

            }// for i
        }

        // 处理推荐算法推荐数据存在相同护工的bug
        if(!CollectionUtils.isEmpty(careList)) {
            List<TbUser> onlyUserList = new ArrayList<>();
            for(TbUser user : careList) {
                if(!onlyUserList.contains(user)) {
                    onlyUserList.add(user);
                }
            }
            careList = onlyUserList;
        }

        if(careList.size() > 5) {
            return careList.subList(0,5);
        }

        List<Integer> ids = new ArrayList<>();
        for(TbUser care: careList) {
            ids.add(care.getId());
        }

        PageResult<TbUser> pageResult = userService.selectUserNotInId(2, ids, 1, 5);
        List<TbUser> allList = pageResult.getList();

        for(TbUser addUser : allList) {
            careList.add(addUser);
        }
        if(careList.size() > 5) {
            return careList.subList(0, 5);
        }

        return careList;

    }

    //通过计算余弦相似度并取TopN, 返回为uid的用户生成的5个推荐护工
    private List<TbOrder> recommend(Integer uid){

        List<TbOrder> likeLists;                                       //其他用户喜欢的订单护工列表

        List<TbUser> users = userService.selectAllUserByRole(1);                   //所有用户列表
        List<TbOrder> papers = orderService.selectAll();               //所有订单护工列表
        int[][] curMatrix = new int[papers.size()+5][papers.size()+5];   //当前矩阵
        int[][] comMatrix = new int[papers.size()+5][papers.size()+5];   //共现矩阵
        int[] N = new int[papers.size()+5];                              //喜欢每个物品的人数

        for(TbUser user: users){
            if(user.getId()==uid) continue;                    //当前用户则跳过

            likeLists = orderService.selectOrderByUserId(user.getId()); //当前用户的喜欢列表

            for(int i = 0; i < papers.size(); i++)
                for(int j = 0; j < papers.size(); j++)
                    curMatrix[i][j] = 0;                               //清空矩阵

            for(int i = 0; i < likeLists.size(); i++){
                int pid1 = likeLists.get(i).getCareId();
                try {
                    ++N[pid1];
                }catch (Exception e) {

                }

                for(int j = i+1; j < likeLists.size(); j++){
                    int pid2 = likeLists.get(j).getCareId();
                    try{
                        ++curMatrix[pid1][pid2];
                        ++curMatrix[pid2][pid1]; //两两加一
                    }catch (Exception e){}

                }
            }
            //累加所有矩阵, 得到共现矩阵
            for(int i = 0; i < papers.size(); i++){
                for(int j = 0; j < papers.size(); j++){
                    int pid1 = papers.get(i).getCareId(), pid2 = papers.get(j).getCareId();
                    try {
                        comMatrix[pid1][pid2] += curMatrix[pid1][pid2];
                        comMatrix[pid1][pid2] += curMatrix[pid1][pid2];
                    }catch (Exception e){}
                }
            }
        }

        TreeSet<TbOrder> preList = new TreeSet<TbOrder>(new Comparator<TbOrder>() {
            @Override
            public int compare(TbOrder o1, TbOrder o2) {
                if(o1.getW()!=o2.getW())
                    return (int) (o1.getW()-o2.getW())*100;
                else
                    return -(int) (o1.getW()-o2.getW())*100;
            }
        }); //预处理的列表

        likeLists = orderService.selectOrderByUserId(uid);       //当前用户喜欢的订单护工列表
        boolean[] used = new boolean[papers.size()+5];  //判重数组
        for(TbOrder like: likeLists){
            int Nij = 0;                         //既喜欢i又喜欢j的人数
            double Wij;                          //相似度
            TbOrder tmp;                           //当前的订单护工

            int i = like.getCareId();
            for(TbOrder paper: papers){
                if(like.getCareId() == paper.getCareId()) continue;
                int j = paper.getCareId();
                Wij = 0;
                try {
                    Nij = comMatrix[i][j];
                    Wij = (double)Nij/Math.sqrt(N[i]*N[j]); //计算余弦相似度
                }catch (Exception e) {}

                tmp = orderService.selectById(paper.getId());
                tmp.setW(Wij);

                preList.add(tmp);
                /*
                try{
                    if(used[tmp.getCareId()]) continue;
                    preList.add(tmp);
                    used[tmp.getCareId()] = true;
                }catch (Exception e){}
                */

            }
        }

        ArrayList<TbOrder> recomLists = new ArrayList<>();      //生成的推荐结果
        for(int i = 0; preList.size()>0 && i<5; i++){
            recomLists.add(preList.pollLast());
            preList.pollLast();
        }
        if(recomLists.size()<5){
            //推荐数量不满5个, 补足喜欢数最高的
            //TODO recomLists = paperdao.findTopNPapers(recomLists);
        }

        return recomLists;
    }


}
