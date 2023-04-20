package com.codesw.fontviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.widget.AdapterView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class RecentFragmentActivity extends  Fragment  { 
	
	
	private String mPath = "";
	private double key = 0;
	private String path = "";
	private HashMap<String, Object> mp = new HashMap<>();
	private double mSize = 0;
	
	private ArrayList<HashMap<String, Object>> list_map = new ArrayList<>();
	
	private ListView listview1;
	
	private SharedPreferences recent;
	private SharedPreferences files;
	private Intent i = new Intent();
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.recent_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		
		listview1 = (ListView) _view.findViewById(R.id.listview1);
		recent = getContext().getSharedPreferences("recent", Activity.MODE_PRIVATE);
		files = getContext().getSharedPreferences("files", Activity.MODE_PRIVATE);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				i.setClass(getContext(), ViewActivity.class);
				i.putExtra("path", list_map.get((int)_position).get("path").toString());
				startActivity(i);
			}
		});
	}
	
	private void initializeLogic() {
		_getData();
	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		_getData();
	}
	public void _getData () {
		if (!files.getString(String.valueOf((long)(key)), "").equals("")) {
			list_map = new Gson().fromJson(files.getString(String.valueOf((long)(key)), ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		listview1.setAdapter(new Listview1Adapter(list_map));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
	}
	
	
	public void _Size (final double _Size) {
	}
	
	public String size(double size){
		    String hrSize = "";
		double k = size/1024.0;
		    double m = size/(1024.0*1024.0);
		double g = size/(1024.0*1024*1024);
		
		    DecimalFormat dec = new DecimalFormat("0.00");
		
		if (g > 1) {
			        hrSize = dec.format(g).concat(" GB");
		}
		    else if (m > 1) {
			        hrSize = dec.format(m).concat(" MB");
			    } else if (k > 1) {
			        hrSize = dec.format(k).concat(" KB");
			    }
		else {
			hrSize = dec.format(size/1024).concat(" KB");
		}
		
		    return hrSize;
		
	}
	
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.custom, null);
			}
			
			final androidx.cardview.widget.CardView cardview1 = (androidx.cardview.widget.CardView) _view.findViewById(R.id.cardview1);
			final LinearLayout linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final TextView textview3 = (TextView) _view.findViewById(R.id.textview3);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			final TextView textview2 = (TextView) _view.findViewById(R.id.textview2);
			
			mPath = _data.get((int)_position).get("path").toString();
			textview1.setText(Uri.parse(mPath).getLastPathSegment());
			mSize = FileUtil.getFileLength(_data.get((int)_position).get("path").toString());
			textview2.setText(size(mSize));
			textview3.setTypeface(Typeface.createFromFile(mPath));
			
			return _view;
		}
	}
	
	
}