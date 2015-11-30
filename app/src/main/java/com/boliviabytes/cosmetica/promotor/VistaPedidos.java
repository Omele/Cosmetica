package com.boliviabytes.cosmetica.promotor;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.boliviabytes.cosmetica.R;
import com.boliviabytes.cosmetica.catalogo.OnFragmentInteractionListener;
import com.boliviabytes.cosmetica.catalogo.TaskRunner;
import com.boliviabytes.cosmetica.catalogo.WSClient;
import com.boliviabytes.cosmetica.catalogo.WSHandler;
import com.boliviabytes.cosmetica.model.DetallePedido;
import com.boliviabytes.cosmetica.model.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link VistaPedidos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VistaPedidos extends Fragment implements AdapterView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PROMOTOR = "PROMOTOR";
    private OnFragmentInteractionListener mListener;
    private TaskRunner taskRunner;
    private  Integer promotorId;
    private List<Pedido> lPedidos;
    public final static ArrayList<DetallePedido> lDetallePedido=new ArrayList<>();;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param promotorId Parameter 1.
     * @return A new instance of fragment VistaPedidos.
     */
    // TODO: Rename and change types and number of parameters
    public static VistaPedidos newInstance(int promotorId) {
        VistaPedidos fragment = new VistaPedidos();
        Bundle args = new Bundle();
        args.putInt(PROMOTOR, promotorId);
        fragment.setArguments(args);
        return fragment;
    }

    public VistaPedidos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            promotorId = getArguments().getInt(PROMOTOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (promotorId!=null&promotorId>0){
            taskRunner= WSClient.getInstance().wsConsultarPedidos(wsHandler, promotorId);
        }
        return inflater.inflate(R.layout.fragment_vista_pedidos, container, false);
    }
    WSHandler wsHandler=new WSHandler(){
        @Override
        public void dispatchMessage(Message msg) {
            actualizarVista();
        }
    };
    private void actualizarVista() {
        try {
            lPedidos= (List<Pedido>) taskRunner.get();
            ListView lvPedidos= (ListView) getView().findViewById(R.id.lvPromotorPedidos);
            if(lPedidos!=null){
                ArrayAdapter<Pedido> arPedidos=new ArrayAdapter<Pedido>(getContext(),android.R.layout.simple_list_item_1,lPedidos);
                lvPedidos.setAdapter(arPedidos);
            }else{
                ArrayAdapter<Pedido> arPedidos=new ArrayAdapter<Pedido>(getContext(),android.R.layout.simple_list_item_1,new ArrayList<Pedido>());
                lvPedidos.setAdapter(arPedidos);

            }

        } catch (InterruptedException e) {
            // e.printStackTrace();
        } catch (ExecutionException e) {
            // e.printStackTrace();
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public void addOnFragmentInteractionListener(OnFragmentInteractionListener mListener){
        this.mListener=mListener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
