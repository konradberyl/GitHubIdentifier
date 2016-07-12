package example.com.githubidentifier;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyInfoFragment extends Fragment {
    private TextView userName;
    private TextView userId;
    private TextView userLogin;
    private TextView userAccountType;
    private TextView userGitHubAddress;
    private TextView userEmailAddress;
    private TextView numberOfRepositories;
    private TextView accountCreatedDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_info_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userName = (TextView) view.findViewById(R.id.user_name_tv);
        userId =(TextView)view.findViewById(R.id.user_id_tv);
        userLogin =(TextView)view.findViewById(R.id.user_login_tv);
        userAccountType= (TextView)view.findViewById(R.id.user_account_type_tv);
        userGitHubAddress=(TextView)view.findViewById(R.id.user_github_address_tv);
        userEmailAddress=(TextView)view.findViewById(R.id.user_email_address_tv);
        numberOfRepositories=(TextView)view.findViewById(R.id.number_of_public_repozitories_tv);
        accountCreatedDate= (TextView)view.findViewById(R.id.account_from_tv);
    }

    public void setDataFromRetrofit(UserDataModel userDataModel){
        if(userDataModel!=null){
            if(userDataModel.name==null){
                userName.setText("brak danych");
            }
            else{
                userName.setText(String.valueOf(userDataModel.name));
            }
            userId.setText(String.valueOf(userDataModel.id));
            userLogin.setText(userDataModel.login);
            userAccountType.setText(userDataModel.type);
            userGitHubAddress.setText(userDataModel.htmlUrl);
            if(userDataModel.email==null){
                userEmailAddress.setText("brak danych");
            }
            else{
                userEmailAddress.setText(String.valueOf(userDataModel.email));
            }
            numberOfRepositories.setText(String.valueOf(userDataModel.publicRepos));
            accountCreatedDate.setText(userDataModel.createdAt);


        }
    }
}
