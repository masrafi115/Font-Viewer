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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import com.google.android.material.button.*;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.AdapterView;
import android.view.View;
import com.google.gson.Gson;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class FontsFragmentActivity extends  Fragment  { 
	
	
	private String mPath = "";
	private String mFilePath = "";
	private double n1 = 0;
	private double n2 = 0;
	private HashMap<String, Object> mp = new HashMap<>();
	private String path = "";
	private double key = 0;
	private double size = 0;
	private String File_Size = "";
	private double mSize = 0;
	
	private ArrayList<String> searchList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> mediaList = new ArrayList<>();
	private ArrayList<String> tembList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> list_map = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ListView listview1;
	private MaterialButton materialbutton1;
	
	private Intent i = new Intent();
	private SharedPreferences files;
	private SharedPreferences file;
	private Intent in = new Intent();
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.fonts_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		
		linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
		linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
		listview1 = (ListView) _view.findViewById(R.id.listview1);
		materialbutton1 = (MaterialButton) _view.findViewById(R.id.materialbutton1);
		files = getContext().getSharedPreferences("files", Activity.MODE_PRIVATE);
		file = getContext().getSharedPreferences("file", Activity.MODE_PRIVATE);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				mp = new HashMap<>();
				mp.put("path", mediaList.get((int)_position).get("media").toString());
				list_map.add((int)0, mp);
				files.edit().putString(String.valueOf((long)(key)), new Gson().toJson(list_map)).commit();
				i.setClass(getContext(), ViewActivity.class);
				i.putExtra("path", mediaList.get((int)_position).get("media").toString());
				startActivity(i);
			}
		});
		
		materialbutton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getContext(), FolderPickerActivity.class);
				in.putExtra("extension", ".ttf, .otf");
				in.putExtra("type", "folder");
				startActivity(in);
			}
		});
	}
	
	private void initializeLogic() {
		mediaList.clear();
		if (file.getString("picked_folder", "").equals("")) {
			linear2.setVisibility(View.VISIBLE);
			linear1.setVisibility(View.GONE);
		}
		else {
			linear2.setVisibility(View.GONE);
			linear1.setVisibility(View.VISIBLE);
		}
		if (!file.getString("picked_folder", "").equals("")) {
			mFilePath = file.getString("picked_folder", "");
			_loadMedia(mFilePath, "ttf", "otf", listview1);
		}
		else {
			
		}
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
	public void onStart() {
		super.onStart();
		mediaList.clear();
		if (!file.getString("picked_folder", "").equals("")) {
			mFilePath = file.getString("picked_folder", "");
			_loadMedia(mFilePath, "ttf", "otf", listview1);
		}
		else {
			
		}
	}
	public void _loadMedia (final String _path, final String _e1, final String _e2, final ListView _lv) {
		new AsyncTask<String,Integer,String>(){
			@Override
			protected String doInBackground(String[] p1){
				n1 = 0;
				FileUtil.listDir(_path, searchList);
				for(int _repeat13 = 0; _repeat13 < (int)(searchList.size()); _repeat13++) {
					if (FileUtil.isFile(searchList.get((int)(n1)))) {
						if (searchList.get((int)(n1)).endsWith(_e1) || searchList.get((int)(n1)).endsWith(_e2)) {
							{
								HashMap<String, Object> _item = new HashMap<>();
								_item.put("media", searchList.get((int)(n1)));
								mediaList.add(_item);
							}
							
						}
					}
					else {
						if (FileUtil.isDirectory(searchList.get((int)(n1)))) {
							n2 = 0;
							FileUtil.listDir(searchList.get((int)(n1)), tembList);
							for(int _repeat38 = 0; _repeat38 < (int)(tembList.size()); _repeat38++) {
								searchList.add(tembList.get((int)(n2)));
								n2++;
							}
						}
					}
					tembList.clear();
					n1++;
				}
				return null;
			}
			@Override
			protected void onPreExecute(){
				super.onPreExecute();
			}
			@Override
			protected void onPostExecute(String result){
				_lv.setAdapter(new Listview1Adapter(mediaList));
				((BaseAdapter)_lv.getAdapter()).notifyDataSetChanged();
				
				super.onPostExecute(result);
			}}.execute();
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
			
			mPath = _data.get((int)_position).get("media").toString();
			textview1.setText(Uri.parse(mPath).getLastPathSegment());
			mSize = FileUtil.getFileLength(_data.get((int)_position).get("media").toString());
			textview2.setText("//File Length");
			textview2.setText(size(mSize));
			textview3.setTypeface(Typeface.createFromFile(mPath));
			
			return _view;
		}
	}
	
	
}