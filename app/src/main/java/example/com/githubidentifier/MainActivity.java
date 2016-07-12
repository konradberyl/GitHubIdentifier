package example.com.githubidentifier;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyInfoFragment myInfoFragmentFragment;
    private TextView userNameHeader;
    private CircleImageView profileImage;
    private RepositioriesFragment repositioriesFragmentFragment;
    private String url = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        userNameHeader = (TextView) findViewById(R.id.user_name_in_header_tv);
        profileImage = (CircleImageView) findViewById(R.id.profile_image);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        myInfoFragmentFragment = new MyInfoFragment();
        repositioriesFragmentFragment = new RepositioriesFragment();
        adapter.addFragment(myInfoFragmentFragment, "DANE");
        adapter.addFragment(repositioriesFragmentFragment, "REPOZYTORIA");
        viewPager.setAdapter(adapter);
        getDataFromServer();
    }

    private void getDataFromServer() {

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();
        RetrofitInterface retrofitInterface = restAdapter.create(RetrofitInterface.class);
        retrofitInterface.getUser(new Callback<UserDataModel>() {
            @Override
            public void success(UserDataModel userDataModel, Response response) {
                if (myInfoFragmentFragment != null && repositioriesFragmentFragment != null) {
                    if (userDataModel.name == null) {
                        userNameHeader.setText("witaj!");
                    } else {
                        userNameHeader.setText("witaj " + userDataModel.name + "!");
                    }
                    Picasso.with(getApplicationContext()).load(userDataModel.avatarUrl).noFade().into(profileImage);
                    myInfoFragmentFragment.setDataFromRetrofit(userDataModel);
                }

            }

            @Override
            public void failure(RetrofitError error) {
                String err = error.getMessage();
            }
        });


    retrofitInterface.getRepos(new Callback<List<UserReposDataModel>>() {
        @Override
        public void success(List<UserReposDataModel> userReposDataModels, Response response) {
            if (myInfoFragmentFragment != null && repositioriesFragmentFragment != null) {
                repositioriesFragmentFragment.setDataFromRetrofit(userReposDataModels);

            }
        }

        @Override
        public void failure(RetrofitError error) {

        }
    });

    }




    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}