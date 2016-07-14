package cn.dpocket.moplusand.uinew.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.dpocket.moplusand.uinew.R;

public class ViewPagerFragment extends Fragment {

    private int mPage;
    private ListView mListView;

    private MyListAdapter mAdapter;

    public static ViewPagerFragment create(int page) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.mPage = page;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, null);
        mListView = (ListView) view.findViewById(R.id.view_pager_list_view);

        View headerView = inflater.inflate(R.layout.view_pager_fragment_list_view_header, null);
        TextView mHeaderTextView = (TextView) headerView.findViewById(R.id.view_pager_fragment_list_view_header_title);
        mHeaderTextView.setBackgroundColor(0xff4d90fe * mPage / 30);
        mHeaderTextView.setText("Page: " + mPage);

        mListView.addHeaderView(headerView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0) {
//					JsonData js = mAdapter.getItem(position);
//                    final String url = js!=null?js.optString("pic"):null;
//                    if (!TextUtils.isEmpty(url)) {
//                        getContext().pushFragmentToBackStack(MaterialStyleFragment.class, url);
//                    }
                }
            }
        });

        initListAllPersons();
        mAdapter = new MyListAdapter(this.getContext(), persons);
//        mAdapter.setViewHolderClass(this, ImageListViewHolder.class, mImageLoader);
        mListView.setAdapter(mAdapter);
        return view;
    }

//    public void update(JsonData data) {
//        mAdapter.getDataList().clear();
//        mAdapter.getDataList().addAll(data.optJson("data").optJson("list").toArrayList());
//        mAdapter.notifyDataSetChanged();
//    }

    public void show() {

    }

    public void hide() {

    }

    public boolean checkCanDoRefresh() {
        if (mAdapter.getCount() == 0 || mListView == null) {
            return true;
        }
        return mListView.getFirstVisiblePosition() == 0 && mListView.getChildAt(0).getTop() == 0;
    }
    private List<Person> persons =null;

    public void initListAllPersons(){
        persons = new ArrayList<Person>();
        persons.add(new Person(100,"李小龙","香港",android.R.drawable.ic_delete));
        persons.add(new Person(101,"施瓦辛格","美国",android.R.drawable.ic_dialog_alert));
        persons.add(new Person(102,"戴安娜王妃","英国",android.R.drawable.ic_dialog_dialer));
        persons.add(new Person(103,"成龙","香港",android.R.drawable.ic_dialog_map));
        persons.add(new Person(104,"史泰龙","美国",android.R.drawable.ic_lock_idle_alarm));
        persons.add(new Person(105,"圣女贞德","法国",android.R.drawable.ic_media_next));
        persons.add(new Person(106,"列宁","苏联",android.R.drawable.ic_media_play));
        persons.add(new Person(107,"爱迪生","美国",android.R.drawable.ic_menu_add));
        persons.add(new Person(108,"孔子","中国",android.R.drawable.ic_media_rew));
        persons.add(new Person(109,"杨七郎","宋朝",android.R.drawable.ic_menu_zoom));
        persons.add(new Person(110,"秦始皇","秦国",android.R.drawable.ic_menu_agenda));
        persons.add(new Person(111,"岳飞","宋朝",android.R.drawable.ic_lock_lock));
    }
    public class Person {

        public int id;
        public String name;
        public String address;
        public int photo;

        public Person(int id, String name, String address,int photo) {
            super();
            this.id = id;
            this.name = name;
            this.address = address;
            this.photo = photo;
        }
    }
    public class MyListAdapter extends BaseAdapter {
        private List<Person> persons;
        Context context;

        public MyListAdapter(Context context,List<Person> persons){
            this.persons = persons;
            this.context = context;
        }

        @Override
        public int getCount() {
            return (persons==null)?0:persons.size();
        }

        @Override
        public Object getItem(int position) {
            return persons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        public class ViewHolder{
            TextView textViewItem01;
            TextView textViewItem02;
            TextView textViewItem03;
            ImageView imageView;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Person person = (Person)getItem(position);
            ViewHolder viewHolder = null;
            if(convertView==null){

                convertView = LayoutInflater.from(context).inflate(
                        R.layout.listviewltem, null);

                viewHolder = new ViewHolder();
                viewHolder.textViewItem01 = (TextView)convertView.findViewById(
                        R.id.listView01Item01);
                viewHolder.textViewItem02 = (TextView)convertView.findViewById(
                        R.id.listView01Item02);
                viewHolder.textViewItem03 = (TextView)convertView.findViewById(
                        R.id.listView01Item03);

                //动态增加1个ImageView
                viewHolder.imageView = new ImageView(context);
                LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                mParams.gravity = Gravity.CENTER;
                mParams.width=50;
                viewHolder.imageView.setLayoutParams(mParams);
                //这个ImageView放到ListView的第2列之后
                ((LinearLayout)convertView).addView(viewHolder.imageView,2);

                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)convertView.getTag();

            }

            viewHolder.textViewItem01.setText(String.valueOf(person.id));
            viewHolder.textViewItem02.setText(person.name);
            viewHolder.textViewItem03.setText(person.address);
            viewHolder.imageView.setImageResource(person.photo);

            //对ListView中第1个TextView配置OnClick事件
            viewHolder.textViewItem01.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,
                            "[textViewItem01.setOnClickListener]点击了"+person.name,
                            Toast.LENGTH_SHORT).show();
                }
            });

            //对ListView中的每一行信息配置OnClick事件
            convertView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Toast.makeText(context,
                            "[convertView.setOnClickListener]点击了"+person.name,
                            Toast.LENGTH_SHORT).show();
                }

            });

            //对ListView中的每一行信息配置OnLongClick事件
            convertView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(context,
                            "[convertView.setOnLongClickListener]点击了"+person.name,
                            Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            return convertView;
        }

    }
}
