package example.com.githubidentifier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class RepositioriesFragment extends Fragment {
  private  RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.repositories_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= (RecyclerView)view.findViewById(R.id.recyclerview_with_repo_info);
    }

    public void setDataFromRetrofit(List<UserReposDataModel> userReposDataModel){
        if(userReposDataModel !=null){
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter();
            recyclerViewAdapter.setConfigToAdapter(userReposDataModel);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerViewAdapter);


        }
    }
}
