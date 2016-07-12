package example.com.githubidentifier;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface RetrofitInterface {
    @GET("/users/konradberyl")
    void getUser(Callback<UserDataModel> userDataModelCallback);

    @GET("/users/konradberyl/repos")
    void getRepos(Callback<List<UserReposDataModel>>userRepoModelCallback);

}
