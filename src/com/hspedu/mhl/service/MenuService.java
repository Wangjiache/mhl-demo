package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.MenuDAO;
import com.hspedu.mhl.domain.Menu;

import java.util.List;

// 完成对menu表的各种操作（通过调用menudao）
public class MenuService {
    //定义menudao属性
        private MenuDAO menuDAO = new MenuDAO();
    //返回所有菜品,给界面使用
    public List<Menu> list (){
        return menuDAO.queryMulti("select * from menu",Menu.class);
    }
    //根据id返回menu对象
    public Menu getMenuById(int id){
        return menuDAO.querySingle("select * from menu where id = ?", Menu.class,id);
    }
}
