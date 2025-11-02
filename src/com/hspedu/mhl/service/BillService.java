package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.BillDAO;
import com.hspedu.mhl.dao.DiningTableDAO;
import com.hspedu.mhl.dao.MultiTableDAO;
import com.hspedu.mhl.domain.Bill;
import com.hspedu.mhl.domain.MultiTableBean;

import java.util.List;
import java.util.UUID;

//处理账单相关业务
public class BillService {
    //定义billdao属性
        private BillDAO billDAO = new BillDAO();
    //定义MenuSERVICE 属性
    private MenuService menuService = new MenuService();
    //定义diningtableservice属性
    private DiningTableService diningTableService = new DiningTableService();
    //
    private MultiTableDAO multiTableDAO = new MultiTableDAO();

    //编写点餐方法
    //生成账单
    //更新餐桌状态
    //如果成功返回true，否则false



    //这行代码中的（int menuId,int nums,int diningTableId）起到什么作用？？？
    public boolean orderMenu(int menuId,int nums,int diningTableId){
        //生成一个账单号。uuid
        String billID = UUID.randomUUID().toString();
        //将账单生成到bill
        int update = billDAO.update("insert into bill values(null,?,?,?,?,?,now(),'未结账')",
                billID, menuId, nums, menuService.getMenuById(menuId).getPrice() * nums, diningTableId);
     if (update <= 0){
         return false;
     }
     //需要更新对应餐桌的状态
        return  diningTableService.updateDiningTableState(diningTableId,"就餐中");
    }

    //返回所有的账单，提供给view调用
    public List<Bill> list(){

        return billDAO.queryMulti("select * from bill", Bill.class);
    }

    //返回所有的账单带有菜品，提供给view调用
    public List<MultiTableBean> list2(){

        return multiTableDAO.queryMulti("SELECT bill.*, NAME " + "FROM bill, menu "
                + "WHERE bill.menuId = menu.id" , MultiTableBean.class);
    }

    //查看某个餐桌是否有未结账的账单
    public boolean hasPayBillByDiningTableId(int diningTableId){
        Bill bill =
                billDAO.querySingle("SELECT * FROM bill WHERE diningTableId = ? AND state = '未结账'  LIMIT 0 ,1", Bill.class, diningTableId);
        return bill != null;
    }
    //完成结账，餐桌存在并且未结账
    public boolean payBill(int diningTableId,String payMode){

        //1.修改bill表
        int update = billDAO.update("update bill set state = ? where diningTableId=? and state = '未结账'", payMode, diningTableId);
        if(update <= 0){
            return false;
        }
        //2.修改diningtable表
        if (!diningTableService.updateDiningTableToFree(diningTableId,"空")){
            return false;
        }
        return true;
    }

}
