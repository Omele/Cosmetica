package com.boliviabytes.cosmetica.catalogo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.boliviabytes.cosmetica.R;
import com.boliviabytes.cosmetica.model.Categoria;

import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {} interface
 * to handle interaction events.
 * Use the {@link VistaCatalogo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VistaCatalogo extends Fragment  implements  AdapterView.OnItemSelectedListener{
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment VistaCatalogo.
     */
    // TODO: Rename and change types and number of parameters
    private  TaskRunner taskRunner;
    public static VistaCatalogo newInstance() {
        VistaCatalogo fragment = new VistaCatalogo();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public VistaCatalogo() {
        // Required empty public constructor
    }
    public void addOnFragmentInteractionListener(com.boliviabytes.cosmetica.catalogo.OnFragmentInteractionListener mListener){
        this.mListener=mListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        taskRunner=WSClient.getInstance().Hello(wsHandler);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vista_catalogo, container, false);

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

    WSHandler wsHandler=new WSHandler(){
        @Override
        public void dispatchMessage(Message msg) {
            actualizarVista();
        }
    };
    public void actualizarVista(){

        try {
            List<Categoria> categorias= (List<Categoria>) taskRunner.get();
            Spinner sCategoria = (Spinner) getView().findViewById(R.id.sCategoria);
            sCategoria.setAdapter(new CategoriaAdapter(getContext(), R.layout.custom_spinner, R.id.tvNombreCategoria, categorias));

            sCategoria.setOnItemSelectedListener(this);
        } catch (InterruptedException e) {
            // e.printStackTrace();
        } catch (ExecutionException e) {
            // e.printStackTrace();
        }

    }



    /**
	 * 
	 * @param categoria
	 */
	public void consultarProductos(int categoria){

	}



	/**
	 * 
	 * @param id
	 */
	public void visualizarProducto(int id){

	}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(),"thississisiisis",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getContext(),VistaCatalogoPrincipal.class);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public class CategoriaAdapter extends ArrayAdapter<Categoria> {

        public CategoriaAdapter(Context context, int resource, int textViewResourceId, List<Categoria> objects) {
            super(context, resource, textViewResourceId, objects);
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position,convertView,parent);
        }
        /**
         * Obtiene un vista personalizada  en un View para elemento
         */
        public View getCustomView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            View vSpinner = inflater.inflate(R.layout.custom_spinner, parent, false);
            TextView tvNombre = (TextView) vSpinner .findViewById(R.id.tvNombreCategoria);
            tvNombre.setText(getItem(position).getNombre());
            return vSpinner;
        }


    }
}
