package com.boliviabytes.cosmetica.catalogo;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.boliviabytes.cosmetica.R;
import com.boliviabytes.cosmetica.model.Categoria;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {} interface
 * to handle interaction events.
 * Use the {@link VistaInicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VistaInicio extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private  TaskRunner taskRunner;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VistaInicio.
     */
    // TODO: Rename and change types and number of parameters
    public static VistaInicio newInstance(String param1, String param2) {
        VistaInicio fragment = new VistaInicio();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void addOnFragmentInteractionListener(OnFragmentInteractionListener mListener){
        this.mListener=mListener;
    }
    public VistaInicio() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

       taskRunner=WSClient.getInstance().Hello(wsHandler);
    }
    WSHandler wsHandler=new WSHandler(){
        @Override
        public void dispatchMessage(Message msg) {
            actualizarVista();
        }
    };
    public void actualizarVista(){

        try {
            List<Categoria>  categorias= (List<Categoria>) taskRunner.get();
            Spinner sCategoria = (Spinner) getView().findViewById(R.id.sCategoria);
            sCategoria.setAdapter(new CategoriaAdapter(getContext(),R.layout.custom_spinner,R.id.tvNombreCategoria, categorias));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vista_inicio, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
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
