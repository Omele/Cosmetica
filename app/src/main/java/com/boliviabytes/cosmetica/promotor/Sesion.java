package com.boliviabytes.cosmetica.promotor;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.boliviabytes.cosmetica.R;
import com.boliviabytes.cosmetica.catalogo.TaskRunner;
import com.boliviabytes.cosmetica.catalogo.VistaPrincipal;
import com.boliviabytes.cosmetica.catalogo.WSClient;
import com.boliviabytes.cosmetica.catalogo.WSHandler;


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
    public static final String PROMOTOR_ID = "PROMOTOR_ID";
    private  TaskRunner taskRunner;
    private Button  btnLogin;
    private TextView login, password;
    private Integer promotorId;
    private SharedPreferences preferencias;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Sesion.
     */
    // TODO: Rename and change types and number of parameters
    public static Sesion newInstance() {
        Sesion fragment = new Sesion();
        Bundle args = new Bundle();
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
            //mParam1 = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
        preferencias = getActivity().getSharedPreferences(VistaPrincipal.SHARE_SESION, Context.MODE_PRIVATE);
    }
    public void addOnFragmentInteractionListener( OnFragmentInteractionListener interactionListener){
        this.mListener=interactionListener;
    }

    WSHandler wsHandler=new WSHandler(){
        @Override
        public void dispatchMessage(Message msg) {
            actualizarVista();
        }
    };
    public void actualizarVista(){

        try {
                promotorId= (Integer) taskRunner.get();
            if(promotorId!=null&&promotorId==0)
                Toast.makeText(getContext(), "Usuario incorecto", Toast.LENGTH_SHORT).show();
            else if(promotorId!=null&&promotorId>0){
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putInt(PROMOTOR_ID,promotorId);
                    editor.commit();
                    mListener.onFragmentInteraction(true);
                    Toast.makeText(getContext(), "Usuario valido", Toast.LENGTH_SHORT).show();

                }

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
                taskRunner=WSClient.getInstance().wsLogin(wsHandler,login.getText().toString(),password.getText().toString());

            }
        });


        return view;
    }

    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        void  onFragmentInteraction(boolean show);
    }


    /**
     *
     * @param password
     * @param login
     */
    public void login(String password, String login){

    }

}
