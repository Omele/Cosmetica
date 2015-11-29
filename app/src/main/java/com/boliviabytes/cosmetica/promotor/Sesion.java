package com.boliviabytes.cosmetica.promotor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.boliviabytes.cosmetica.R;
import com.boliviabytes.cosmetica.catalogo.OnFragmentInteractionListener;
import com.boliviabytes.cosmetica.catalogo.TaskRunner;
import com.boliviabytes.cosmetica.catalogo.WSClient;
import com.boliviabytes.cosmetica.catalogo.WSHandler;
import com.boliviabytes.cosmetica.model.Categoria;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {} interface
 * to handle interaction events.
 * Use the {@link Sesion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sesion extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private  TaskRunner taskRunner;
    private OnFragmentInteractionListener mListener;
    private Button  btnLogin;
    private TextView login, password;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sesion.
     */
    // TODO: Rename and change types and number of parameters
    public static Sesion newInstance(String param1, String param2) {
        Sesion fragment = new Sesion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Sesion() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //taskRunner=WSClient.getInstance().wsLogin(wsHandler,"ygr","ygr");
        //Toast.makeText(getContext(), "Este es ", Toast.LENGTH_SHORT).show();

    }

    WSHandler wsHandler=new WSHandler(){
        @Override
        public void dispatchMessage(Message msg) {
            actualizarVista();
        }
    };
    public void actualizarVista(){

        try {
            Integer validar= (Integer) taskRunner.get();
            if(validar==0)
                Toast.makeText(getContext(), "Usuario incorecto", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(), "Usuario valido", Toast.LENGTH_SHORT).show();

        } catch (InterruptedException e) {
            // e.printStackTrace();
        } catch (ExecutionException e) {
            // e.printStackTrace();
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);
        btnLogin=(Button)view.findViewById(R.id.btnLogin);
        login=(TextView) view.findViewById(R.id.txtLogin);
        password=(TextView) view.findViewById(R.id.txtPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("","enviando los adtos");

                taskRunner=WSClient.getInstance().wsLogin(wsHandler,login.getText().toString(),password.getText().toString());

            }
        });


        return view;
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

    public void addOnFragmentInteractionListener(OnFragmentInteractionListener mListener){
        this.mListener=mListener;
    }
    /**
     *
     * @param password
     * @param login
     */
    public void login(String password, String login){

    }

}
