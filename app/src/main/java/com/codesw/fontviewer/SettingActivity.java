package com.codesw.fontviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
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
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class SettingActivity extends  AppCompatActivity  { 
	
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String item_ = "";
	private double wh_ = 0;
	private double textSize = 0;
	private String dummy = "";
	
	private ArrayList<String> text_align = new ArrayList<>();
	
	private LinearLayout main_set;
	private LinearLayout ui_lin;
	private LinearLayout v_lin;
	private LinearLayout email_lin;
	private LinearLayout tele_lin;
	private LinearLayout pp_lin;
	private TextView textview17;
	private ImageView imageview3;
	private TextView version_txt;
	private TextView textview41;
	private TextView textview32;
	private TextView textview33;
	private TextView textview49;
	private TextView textview55;
	
	private Intent i = new Intent();
	private AlertDialog.Builder d;
	private SharedPreferences settings;
	private AlertDialog.Builder dialog_add;
	private AlertDialog.Builder align;
	private AlertDialog.Builder di;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.setting);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_app_bar = (AppBarLayout) findViewById(R.id._app_bar);
		_coordinator = (CoordinatorLayout) findViewById(R.id._coordinator);
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		main_set = (LinearLayout) findViewById(R.id.main_set);
		ui_lin = (LinearLayout) findViewById(R.id.ui_lin);
		v_lin = (LinearLayout) findViewById(R.id.v_lin);
		email_lin = (LinearLayout) findViewById(R.id.email_lin);
		tele_lin = (LinearLayout) findViewById(R.id.tele_lin);
		pp_lin = (LinearLayout) findViewById(R.id.pp_lin);
		textview17 = (TextView) findViewById(R.id.textview17);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		version_txt = (TextView) findViewById(R.id.version_txt);
		textview41 = (TextView) findViewById(R.id.textview41);
		textview32 = (TextView) findViewById(R.id.textview32);
		textview33 = (TextView) findViewById(R.id.textview33);
		textview49 = (TextView) findViewById(R.id.textview49);
		textview55 = (TextView) findViewById(R.id.textview55);
		d = new AlertDialog.Builder(this);
		settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);
		dialog_add = new AlertDialog.Builder(this);
		align = new AlertDialog.Builder(this);
		di = new AlertDialog.Builder(this);
		
		ui_lin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				di.setTitle("Default Search Engine");
				di.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						SketchwareUtil.showMessage(getApplicationContext(), item_);
						if (item_.equals("Top")) {
							settings.edit().putString("align", "Top").commit();
						}
						if (item_.equals("Center")) {
							settings.edit().putString("align", "Center").commit();
						}
						if (item_.equals("Bottom")) {
							settings.edit().putString("align", "Bottom").commit();
						}
					}
				});
				_Single_Choice_Dialog(di, text_align);
				di.create().show();
			}
		});
		
		v_lin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d.setTitle("Default Text Size");
				if (!settings.getString("text_size", "").equals("")) {
					textSize = Double.parseDouble(settings.getString("text_size", ""));
				}
				else {
					textSize = 25;
				}
				LayoutInflater inflater = getLayoutInflater();
				
				View convertView = (View) inflater.inflate(R.layout.text_size, null);
				d.setView(convertView);
				
				final TextView d_textview1 = (TextView) convertView.findViewById(R.id.textview1);//on custome_dialog
				
				SeekBar d_seekbar1 = (SeekBar) convertView.findViewById(R.id.seekbar1);//on custome_dialog
				
				d_seekbar1.setProgress((int)textSize);
				d_textview1.setText(String.valueOf((long)(textSize)));
				
				d_seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
								@Override
								public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
										final int _progressValue = _param2;
						d_textview1.setText(String.valueOf((long)(_progressValue)));
						textSize = _progressValue;
										
						
					}@Override
								public void onStartTrackingTouch(SeekBar _param1) {
										
								}
								
								@Override
								public void onStopTrackingTouch(SeekBar _param2) {
										
								}
						});
				d.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						settings.edit().putString("text_size", String.valueOf((long)(textSize))).commit();
					}
				});
				d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.create().show();
			}
		});
		
		email_lin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog_add.setTitle("Edit");
				dialog_add.setMessage("Leave Blank For Default");
				if (!settings.getString("dummy_text", "").equals("")) {
					dummy = settings.getString("dummy_text", "");
				}
				else {
					dummy = "Loream ipsum dolor sit amet";
				}
				final EditText dialog_edittext2 = new EditText(SettingActivity.this);
				dialog_edittext2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
				dialog_edittext2.setHint("Enter Text");
				dialog_edittext2.setText(dummy);
				
				View inflated_view = getLayoutInflater().inflate(R.layout.dialog_view, null);
				
				LinearLayout dialog_linear2 = inflated_view.findViewById(R.id.linear3);
				
				dialog_linear2.addView(dialog_edittext2);
				dialog_add.setView(inflated_view);
				dialog_add.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						dummy = dialog_edittext2.getText().toString();
						settings.edit().putString("dummy_text", dummy).commit();
					}
				});
				dialog_add.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog_add.create().show();
			}
		});
		
		pp_lin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				startActivity(i);
			}
		});
	}
	
	private void initializeLogic() {
		text_align.add("Top");
		text_align.add("Center");
		text_align.add("Bottom");
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _Single_Choice_Dialog (final AlertDialog.Builder _dialog, final ArrayList<String> _list) {
		final CharSequence[] _items = _list.toArray(new String[_list.size()]);
		_dialog.setSingleChoiceItems(_items, -1, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				wh_ = which;
				item_ = _list.get((int)(wh_));
				
			}});
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