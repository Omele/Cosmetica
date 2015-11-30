package com.boliviabytes.cosmetica.catalogo;

import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.boliviabytes.cosmetica.R;
import com.boliviabytes.cosmetica.model.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class VistaCatalogoPrincipal extends AppCompatActivity implements  OnFragmentInteractionListener{
    ViewPager mViewPager;
    TaskRunner taskRunner;
    private Integer categoriaID;
    public final static String CATEGORIA_ID="CATEGORIAID";
    public final static Integer CABELLO=0,ROSTRO=1,CUERPO=2;
    public final static ArrayList<Producto> lProductosCabello=new ArrayList<>();;
    public final static ArrayList<Producto> lProductosRostro=new ArrayList<>();;
    public final  static ArrayList<Producto> lProductosCuerpo=new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar(); // Añadir la toolbar
        setToolbar(); // Añadir la toolbar

        // Setear adaptador al viewpager.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(mViewPager);

        // Preparar las pestañas
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);
        categoriaID=getIntent().getIntExtra(CATEGORIA_ID,-1);
        System.out.println(categoriaID);
        if(categoriaID!=-1){
            taskRunner=WSClient.getInstance().wsConsultarProductos(wsHandler,categoriaID);
        }
    }

    public static ArrayList<Producto> getlProductosCabello() {
        return lProductosCabello;
    }

    public static ArrayList<Producto> getlProductosCuerpo() {
        return lProductosCuerpo;
    }

    public static  ArrayList<Producto> getlProductosRostro() {
        return lProductosRostro;
    }

    WSHandler wsHandler=new WSHandler(){
        @Override
        public void dispatchMessage(Message msg) {
            actualizarVista();
        }
    };
    public void actualizarVista(){
        try {
            List<Producto> lProductos = (List<Producto>) taskRunner.get();
            if(lProductos!=null){
                for (Producto producto:lProductos){
                        if (producto.getTipo()==CABELLO){
                            lProductosCabello.add(producto);
                        }else if(producto.getTipo()==ROSTRO){
                            lProductosRostro.add(producto);
                        }else if(producto.getTipo()==CUERPO){
                            lProductosCuerpo.add(producto);
                        }
                }

            }
        } catch (InterruptedException e) {
            // e.printStackTrace();
        } catch (ExecutionException e) {
            // e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_shop);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_shop) {
            showSnackBar("Carrito de compras");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Muestra una {@link Snackbar} prefabricada
     *
     * @param msg Mensaje a proyectar
     */
    private void showSnackBar(String msg) {
        Snackbar.make(findViewById(R.id.fab), msg, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Establece la toolbar como action bar
     */
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    /**
     * Crea una instancia del view pager con los datos
     * predeterminados
     *
     * @param viewPager Nueva instancia
     */
    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        VistaCatalogoProducto vcpCabello= VistaCatalogoProducto.newInstance(CABELLO);
        vcpCabello.addOnFragmentInteractionListener(this);
        adapter.addFragment(vcpCabello, "Cabello");

        VistaCatalogoProducto vcpRostro= VistaCatalogoProducto.newInstance(ROSTRO);
        vcpRostro.addOnFragmentInteractionListener(this);
        adapter.addFragment(vcpRostro, "Rostro");

        VistaCatalogoProducto vcpCuerpo= VistaCatalogoProducto.newInstance(CUERPO);
        vcpCuerpo.addOnFragmentInteractionListener(this);
        adapter.addFragment(vcpCuerpo, "Cuerpo");

        viewPager.setAdapter(adapter);

    }

    /**
     * Método onClick() del FAB
     *
     * @param v View presionado
     */
    public void onFabClick(View v) {
        showSnackBar("Buscar producto");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * Un {@link FragmentPagerAdapter} que gestiona las secciones, fragmentos y
     * títulos de las pestañas
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}
