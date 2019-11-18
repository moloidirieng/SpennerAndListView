package a.mt.spennerandlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spnDanhMuc;
    EditText edtMasp;
    EditText edtTensp;
    ListView lvInformation;
    Button btnNhapSP;

    // cập đối tượng dùng cho Spinner

    ArrayList<Catalog> arraySpinner=new ArrayList<Catalog>();
    ArrayAdapter<Catalog> adapterSpinner=null;

    // cắp đối tượng dùng cho ListView
    ArrayList<Product> arrayListView=new ArrayList<Product>();
    ArrayAdapter<Product> adapterListView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        fakeDataCatalog();
        addEvents();

    }

    private void addEvents() {
        btnNhapSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductForCatalog();
            }
        });
    }

    private void addProductForCatalog() {
        String ma,ten;
        ma=edtMasp.getText().toString().trim();
        ten=edtTensp.getText().toString().trim();

        if(ma.isEmpty()){
            Toast.makeText(this, "Nhập mã", Toast.LENGTH_SHORT).show();
            edtMasp.requestFocus();
        } else if(ten.isEmpty()){
            Toast.makeText(this, "Nhập Tên", Toast.LENGTH_SHORT).show();
            edtTensp.requestFocus();

        }else {
            Product p=new Product();
            p.setId(ma);
            p.setName(ten);
            Catalog c= (Catalog) spnDanhMuc.getSelectedItem();
            c.addProduct(p);
            loadListProductByCatalog(c);
        }
    }

    private void loadListProductByCatalog(Catalog c) {
        // Xóa danh sách cũ
        arrayListView.clear();
        // lấy danh sách mới từ catalog chọn trong Spinner
        arrayListView.addAll(c.getListSp());
        // cập nhật listview
        adapterListView.notifyDataSetChanged();

    }

    private void fakeDataCatalog() {
        Catalog cat1=new Catalog("1","SamSung");
        Catalog cat2=new Catalog("2","Iphone");
        Catalog cat3=new Catalog("3","Ipad");

        arraySpinner.add(cat1);
        arraySpinner.add(cat2);
        arraySpinner.add(cat3);
        adapterSpinner.notifyDataSetChanged();
    }

    private void addControls() {
        spnDanhMuc=findViewById(R.id.spnDanhMuc);
        edtMasp=findViewById(R.id.edtMaSP);
        edtTensp=findViewById(R.id.edtTenSp);
        btnNhapSP=findViewById(R.id.btnNhapSP);
        lvInformation=findViewById(R.id.lvInfomation);


        // cấu hình chó Spinner;
        adapterSpinner =new ArrayAdapter<Catalog>(this,
                android.R.layout.simple_spinner_item,
                arraySpinner);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnDanhMuc.setAdapter(adapterSpinner);

        // cấu hình cho listView
        adapterListView=new ArrayAdapter<Product>(this,
                android.R.layout.simple_list_item_1,arrayListView);

        lvInformation.setAdapter(adapterListView);

    }
}
