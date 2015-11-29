package com.boliviabytes.cosmetica.catalogo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.boliviabytes.cosmetica.R;
import com.boliviabytes.cosmetica.model.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {} interface
 * to handle interaction events.
 * Use the {@link VistaCatalogoProducto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VistaCatalogoProducto extends Fragment {

    public final static String TIPO_PRODUCCTO="TPRODUCTO",TITULO="TITULO";
    private String titulo;
    private int tipoProducto;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param titulo Parameter 1.
     * @param tipoProducto Parameter 2.
     * @return A new instance of fragment VistaCatalogo.
     */
    // TODO: Rename and change types and number of parameters
    public static VistaCatalogoProducto newInstance(String titulo, int tipoProducto) {
        VistaCatalogoProducto fragment = new VistaCatalogoProducto();
        Bundle args = new Bundle();
        args.putInt(TIPO_PRODUCCTO,tipoProducto);
        args.putString(TITULO, titulo);
        fragment.setArguments(args);
        return fragment;
    }

    public VistaCatalogoProducto() {
        // Required empty public constructor
    }
    public void addOnFragmentInteractionListener(OnFragmentInteractionListener mListener){
        this.mListener=mListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_vista_catalogo_filtro, container, false);
        ListView lvProductos= (ListView) view.findViewById(R.id.lv_productos);
        List<Producto> lProductos=new ArrayList<>();
        Producto producto=new Producto();
        producto.setNombre("hhhhhhhh");
        producto.setDescripcion("sjdfsjdfjasdj fajdfjasdfkjsdkfjaksj dfskjd;fkajs;dfkja;sdfkjasf");
        lProductos.add(producto);
         producto=new Producto();
        producto.setNombre("hhhhhhhh");
        producto.setDescripcion("sjdfsjdfjasdj fajdfjasdfkjsdkfjaksj dfskjd;fkajs;dfkja;sdfkjasf");
        lProductos.add(producto);

        lvProductos.setAdapter(new AdapterCatalogo(getContext(),lProductos));

        return view;

    }

    // TODO: Rename method, update argument anlvd hook method into UI event

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public void setLayoutCatalogo(){


    }
    public void setLayoutCatalogoFiltro(){


    }



    /**
	 * 
	 * @param categoria
	 */
	public void consultarProductos(int categoria){

	}

	public void actualizarVista(){

	}

	/**
	 * 
	 * @param id
	 */
	public void visualizarProducto(int id){

	}

}
