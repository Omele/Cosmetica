package com.boliviabytes.cosmetica.catalogo;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

import com.boliviabytes.cosmetica.GridFragment;
import com.boliviabytes.cosmetica.R;
import com.boliviabytes.cosmetica.promotor.Sesion;
import com.boliviabytes.cosmetica.promotor.VistaPedidos;

import java.util.ArrayList;
import java.util.List;

public class VistaPrincipal extends AppCompatActivity implements  OnFragmentInteractionListener{
    ViewPager mViewPager;
    public static final String SHARE_SESION="PREF_SESION";
    private SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       setToolbar(); // Añadir la toolbar

        // Setear adaptador al viewpager.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(mViewPager);

        // Preparar las pestañas
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);
        preferencias = getSharedPreferences(VistaPrincipal.SHARE_SESION, Context.MODE_PRIVATE);
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
        Sesion sesion=Sesion.newInstance();
        sesion.addOnFragmentInteractionListener(fragmentInteractionListener);
        adapter.addFragment(sesion, "Sesion");
        VistaCatalogo vistaCatalogo =VistaCatalogo.newInstance();
        vistaCatalogo.addOnFragmentInteractionListener(this);
        adapter.addFragment(vistaCatalogo, "Catalogo");

        VistaCarrito vistaCarrito=VistaCarrito.newInstance("A","A");
        vistaCarrito.addOnFragmentInteractionListener(this);
        adapter.addFragment(vistaCarrito, "Carrito");
        viewPager.setCurrentItem(2);
        viewPager.setAdapter(adapter);

    }
    Sesion.OnFragmentInteractionListener fragmentInteractionListener =new Sesion.OnFragmentInteractionListener() {
        @Override
        public void onFragmentInteraction(boolean show) {
            if (show){
                agregarVistaPedido();
            }
        }
    };
    public void agregarVistaPedido(){
        Integer promotorId=preferencias.getInt(Sesion.PROMOTOR_ID, -1);
        if(promotorId>0){
            SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
            Sesion sesion=Sesion.newInstance();
            sesion.addOnFragmentInteractionListener(fragmentInteractionListener);
            adapter.addFragment(sesion, "Sesion");
            VistaCatalogo vistaCatalogo =VistaCatalogo.newInstance();
            vistaCatalogo.addOnFragmentInteractionListener(this);
            adapter.addFragment(vistaCatalogo, "Catalogo");

            VistaCarrito vistaCarrito=VistaCarrito.newInstance("A","A");
            vistaCarrito.addOnFragmentInteractionListener(this);
            adapter.addFragment(vistaCarrito, "Carrito");
            VistaPedidos vistaPedidos=VistaPedidos.newInstance(promotorId);
            vistaPedidos.addOnFragmentInteractionListener(this);
            adapter.addFragment(vistaPedidos, "Pedidos");
            mViewPager.setCurrentItem(2);
            mViewPager.setAdapter(adapter);
            // Preparar las pestañas
            TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
            tabs.setupWithViewPager(mViewPager);
        }


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
