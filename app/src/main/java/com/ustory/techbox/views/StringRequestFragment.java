package com.ustory.techbox.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.test2.R;
import com.ustory.techbox.utils.Constants;
import com.ustory.techbox.utils.L;
import com.ustory.techbox.utils.StringUtil;
import com.ustory.techbox.utils.ToastUtil;
import com.ustory.techbox.utils.VolleyUtil;


public class StringRequestFragment extends Fragment implements View.OnClickListener{
	public static final int INDEX = 11;

	private EditText etUrl;
	private Button btnSend;
	private TextView tvResult;
	private Button back;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fr_string_request, container,false);

		initView(view);

		return view;
	}

	private void initView(View view) {
		etUrl = (EditText) view.findViewById(R.id.et_url);
		btnSend = (Button) view.findViewById(R.id.btn_send);
		tvResult = (TextView) view.findViewById(R.id.tv_result);
        back = (Button)view.findViewById(R.id.back);
		etUrl.setText(Constants.DEFAULT_STRING_REQUEST_URL);

		btnSend.setOnClickListener(this);
		back.setOnClickListener(this);


	}

	@Override
	public void onDestroyView() {
		VolleyUtil.getQueue(getActivity()).cancelAll(this);
		super.onDestroyView();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btn_send :
				if (StringUtil.isEmpty(etUrl.getText().toString())) {
					ToastUtil.showToast(getActivity(), "请输入请求地址");
					return;
				}
				//请求之前，先取消之前的请求（取消还没有进行完的请求）
				VolleyUtil.getQueue(getActivity()).cancelAll(this);
				tvResult.setText("");

				StringRequest request = new StringRequest(StringUtil.preUrl(etUrl.getText().toString().trim()),
						new Listener<String>() {

							@Override
							public void onResponse(String response) {
								tvResult.setText(response);

							}
						}, new ErrorListener() {

							@Override
							public void onErrorResponse(VolleyError arg0) {
								ToastUtil.showToast(getActivity(), getResources().getString(R.string.request_fail_text));

							}
				});
				//请求加上Tag,用于取消请求
				request.setTag(this);


				VolleyUtil.getQueue(getActivity()).add(request);

				break;
			case R.id.back:
				 L.i("back");
				 getActivity().finish();
				 break;
            default:
				break;
		}
	}

}
