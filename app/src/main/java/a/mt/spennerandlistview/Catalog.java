package a.mt.spennerandlistview;

import java.util.ArrayList;

public class Catalog extends Goods {
    private ArrayList<Product> listSp=null;

    public Catalog(String id, String name, ArrayList<Product> listSp) {
        super(id, name);
        this.listSp = listSp;
    }

    public Catalog(String id, String name) {
        super(id, name);
        this.listSp=new ArrayList<Product>();
    }

    public Catalog() {
        super();
        this.listSp=new ArrayList<Product>();
    }

    /**
     * kiểm tra sản phâm đã tồn tại trong danh muc catalog chưa
     * param p
     *  return true nếu tồn tại
     * */
    public boolean kiemTratontai(Product p){
        for (Product p1: listSp){
            if(p1.getId().trim().equalsIgnoreCase(p.getId().toString())){
                return true;
            }
        }
        return false;
    }

    /*
    * thêm 1 sản phẩm vào danh mục
    * thêm thành công = true
    * */
    public boolean addProduct(Product p){
        boolean kiemtra=kiemTratontai(p);
        if(!kiemtra){
            p.setDmuc(this);
            return  listSp.add(p);
        }
        return !kiemtra;
    }

    public ArrayList<Product> getListSp() {
        return listSp;
    }

    public int size(){
        return listSp.size();
    }
    public Product get(int i){
        return  listSp.get(i);
    }


    public void setListSp(ArrayList<Product> listSp) {
        this.listSp = listSp;
    }

}
