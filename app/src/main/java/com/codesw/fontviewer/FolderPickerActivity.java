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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.widget.AdapterView;
import android.view.View;
import com.bumptech.glide.Glide;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class FolderPickerActivity extends  AppCompatActivity  { 
	
	
	private String current_filepath = "";
	private double n_f = 0;
	private HashMap<String, Object> map_file = new HashMap<>();
	private String extension = "";
	private double f_position = 0;
	private String path_bind = "";
	private boolean isFolder = false;
	private boolean isFile = false;
	
	private ArrayList<String> file_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> file_lm = new ArrayList<>();
	private ArrayList<String> file_list_2 = new ArrayList<>();
	
	private LinearLayout linear_d_main;
	private LinearLayout linear1;
	private LinearLayout linear_toolbr;
	private ListView listview_d;
	private LinearLayout linear_grid;
	private ImageView d_back;
	private TextView txt_path;
	private ImageView img_switch;
	private TextView textview1;
	private Button button1;
	
	private SharedPreferences file;
	private Intent i = new Intent();
	private Intent it = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.folder_picker);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear_d_main = (LinearLayout) findViewById(R.id.linear_d_main);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear_toolbr = (LinearLayout) findViewById(R.id.linear_toolbr);
		listview_d = (ListView) findViewById(R.id.listview_d);
		linear_grid = (LinearLayout) findViewById(R.id.linear_grid);
		d_back = (ImageView) findViewById(R.id.d_back);
		txt_path = (TextView) findViewById(R.id.txt_path);
		img_switch = (ImageView) findViewById(R.id.img_switch);
		textview1 = (TextView) findViewById(R.id.textview1);
		button1 = (Button) findViewById(R.id.button1);
		file = getSharedPreferences("file", Activity.MODE_PRIVATE);
		
		listview_d.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (isFile) {
					if (file_lm.get((int)_position).get("type").toString().equals("folder")) {
						_go_to(file_lm.get((int)_position).get("path").toString());
						_refresh();
					}
					else {
						i.setClass(getApplicationContext(), ViewActivity.class);
						i.putExtra("path", file_lm.get((int)_position).get("path").toString());
						startActivity(i);
					}
				}
				else {
					if (file_lm.get((int)_position).get("type").toString().equals("folder")) {
						_go_to(file_lm.get((int)_position).get("path").toString());
						_refresh();
					}
					else {
						
					}
				}
			}
		});
		
		d_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		img_switch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (file.getString("filepicker_type", "").equals("listview")) {
					file.edit().putString("filepicker_type", "gridview").commit();
					img_switch.setImageResource(R.drawable.ic_dehaze_grey);
				}
				else {
					file.edit().putString("filepicker_type", "listview").commit();
					img_switch.setImageResource(R.drawable.ic_apps_grey);
				}
				_refresh();
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				file.edit().putString("picked_folder", current_filepath).commit();
				it.setClass(getApplicationContext(), MainActivity.class);
				it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(it);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		if (getIntent().getStringExtra("type").equals("folder")) {
			isFolder = true;
			isFile = false;
		}
		else {
			if (getIntent().getStringExtra("type").equals("file")) {
				linear1.setVisibility(View.GONE);
				isFolder = false;
				isFile = true;
			}
		}
		_go_to(FileUtil.getExternalStorageDir());
		img_switch.setImageResource(R.drawable.ic_apps_grey);
		if (file.getString("filepicker_type", "").equals("")) {
			file.edit().putString("filepicker_type", "listview").commit();
		}
		else {
			if (file.getString("filepicker_type", "").equals("listview")) {
				listview_d.setAdapter(new Listview_dAdapter(file_lm));
			}
			else {
				listview_d.setVisibility(View.GONE);
				img_switch.setImageResource(R.drawable.ic_dehaze_grey);
				_install_gridview();
			}
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		_go_to(current_filepath.substring((int)(0), (int)((current_filepath.length() - Uri.parse(current_filepath).getLastPathSegment().length()) - 1)));
		_refresh();
	}
	public void _go_to (final String _path) {
		if (FileUtil.isDirectory(_path)) {
			current_filepath = _path;
			file_list.clear();
			file_lm.clear();
			file_list_2.clear();
			FileUtil.listDir(_path, file_list);
			Collections.sort(file_list, String.CASE_INSENSITIVE_ORDER);
			n_f = 0;
			for(int _repeat20 = 0; _repeat20 < (int)(file_list.size()); _repeat20++) {
				if (FileUtil.isDirectory(file_list.get((int)(n_f)))) {
					map_file = new HashMap<>();
					map_file.put("type", "folder");
					map_file.put("name", Uri.parse(file_list.get((int)(n_f))).getLastPathSegment());
					map_file.put("path", file_list.get((int)(n_f)));
					file_lm.add(map_file);
				}
				else {
					file_list_2.add(file_list.get((int)(n_f)));
				}
				n_f++;
			}
			file_list.clear();
			n_f = 0;
			for(int _repeat48 = 0; _repeat48 < (int)(file_list_2.size()); _repeat48++) {
				_get_extension(file_list_2.get((int)(n_f)));
				if (getIntent().getStringExtra("extension").contains(extension)) {
					map_file = new HashMap<>();
					map_file.put("type", "file");
					map_file.put("name", Uri.parse(file_list_2.get((int)(n_f))).getLastPathSegment());
					map_file.put("path", file_list_2.get((int)(n_f)));
					file_lm.add(map_file);
				}
				n_f++;
			}
			file_list_2.clear();
			txt_path.setText(_path);
		}
		else {
			
		}
	}
	
	
	public void _refresh () {
		txt_path.setText(current_filepath);
		if (file.getString("filepicker_type", "").equals("listview")) {
			listview_d.setAdapter(new Listview_dAdapter(file_lm));
			listview_d.setVisibility(View.VISIBLE);
		}
		else {
			listview_d.setVisibility(View.GONE);
			_install_gridview();
		}
	}
	
	
	public void _install_gridview () {
		linear_grid.removeAllViews();
		grid = new GridView(FolderPickerActivity.this);
				
				grid.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT));
				
				grid.setNumColumns(3);
				
				grid.setBackgroundColor(Color.WHITE);
				
				grid.setVerticalSpacing(5);
				
				grid.setHorizontalSpacing(5);
				
				grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
				
				grid.setAdapter(new Gridview1Adapter(file_lm));
				
				linear_grid.addView(grid);
				grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						  @Override
						  public void onItemClick(AdapterView parent, View view, int _pos, long id) {
							
				f_position = new Double(_pos);
				if (isFile) {
					if (file_lm.get((int)f_position).get("type").toString().equals("folder")) {
						_go_to(file_lm.get((int)f_position).get("path").toString());
						_refresh();
					}
					else {
						i.setClass(getApplicationContext(), ViewActivity.class);
						i.putExtra("path", file_lm.get((int)f_position).get("path").toString());
						startActivity(i);
					}
				}
				else {
					if (file_lm.get((int)f_position).get("type").toString().equals("folder")) {
						_go_to(file_lm.get((int)f_position).get("path").toString());
						_refresh();
					}
					else {
						
					}
				}
			}});
		
		}
		
		private GridView grid;
		
		
		public class Gridview1Adapter extends BaseAdapter {
						ArrayList<HashMap<String, Object>> _data;
						public Gridview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
						public View getView(final int _position, View _view, ViewGroup _viewGroup) {
									LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
									View _v = _view;
									if (_v == null) {
												_v = _inflater.inflate(R.layout.fp_griditem, null);
									}
									
			final LinearLayout linear_bg = (LinearLayout) _v.findViewById(R.id.linear_bg);
						final ImageView img_file = (ImageView) _v.findViewById(R.id.img_file);
						final TextView txt_filename = (TextView) _v.findViewById(R.id.txt_filename);
						
						if (_data.get((int)_position).get("type").toString().equals("folder")) {
								img_file.setImageResource(R.drawable.folder_80px);
						}
						else {
								path_bind = _data.get((int)_position).get("path").toString();
								if (path_bind.endsWith(".gif") || (path_bind.endsWith(".png") || (path_bind.endsWith(".jpeg") || (path_bind.endsWith(".jpg") || path_bind.endsWith(".apng"))))) {
										_setImageFromFile(img_file, path_bind);
								}
								else {
										img_file.setImageResource(R.drawable.file_80px);
								}
						}
						txt_filename.setText(_data.get((int)_position).get("name").toString());
						
			return _v;}
	}
	
	
	public void _setImageFromFile (final ImageView _img, final String _path) {
		
		java.io.File file = new java.io.File(_path);
		Uri imageUri = Uri.fromFile(file);
		
		Glide.with(getApplicationContext ()).load(imageUri).into(_img);
	}
	
	
	public void _get_extension (final String _path) {
		try{
			extension = _path.substring((int)(_path.lastIndexOf(".")), (int)(_path.length()));
		} catch (Exception e){
			extension = "unknown";
		}
		extension = extension.toLowerCase().trim();
	}
	
	
	public class Listview_dAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview_dAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.fp_item, null);
			}
			
			final LinearLayout linear_bg = (LinearLayout) _view.findViewById(R.id.linear_bg);
			final ImageView img_file = (ImageView) _view.findViewById(R.id.img_file);
			final TextView txt_filename = (TextView) _view.findViewById(R.id.txt_filename);
			
			if (_data.get((int)_position).get("type").toString().equals("folder")) {
				img_file.setImageResource(R.drawable.folder_80px);
			}
			else {
				path_bind = _data.get((int)_position).get("path").toString();
				if (path_bind.endsWith(".gif") || (path_bind.endsWith(".png") || (path_bind.endsWith(".jpeg") || (path_bind.endsWith(".jpg") || path_bind.endsWith(".apng"))))) {
					_setImageFromFile(img_file, path_bind);
				}
				else {
					img_file.setImageResource(R.drawable.file_80px);
				}
			}
			txt_filename.setText(_data.get((int)_position).get("name").toString());
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}