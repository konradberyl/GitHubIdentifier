package example.com.githubidentifier;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<UserReposDataModel> userReposDataModel;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_in_recyclerview,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.repoName.setText(userReposDataModel.get(position).name);
        holder.repoAddress.setText(userReposDataModel.get(position).htmlUrl);
        holder.repoLanguage.setText(userReposDataModel.get(position).language);
        holder.repoTimeAdded.setText(userReposDataModel.get(position).description);
        holder.numberOfRepository.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return userReposDataModel.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
    public  TextView repoName,repoAddress,repoLanguage,repoTimeAdded,numberOfRepository;

        public ViewHolder(View itemView) {
            super(itemView);
            this.repoName = (TextView)itemView.findViewById(R.id.repo_name_tv);
            this.repoAddress = (TextView)itemView.findViewById(R.id.github_address_tv_in_recyclerview);
            this.repoLanguage=(TextView)itemView.findViewById(R.id.language_tv_in_recyclerview);
            this.repoTimeAdded=(TextView)itemView.findViewById(R.id.description);
            this.numberOfRepository=(TextView)itemView.findViewById(R.id.number_of_repository_in_single_card_tv);
        }
    }
    public void setConfigToAdapter(List<UserReposDataModel> userReposDataModel){
        this.userReposDataModel = userReposDataModel;
    }

}
