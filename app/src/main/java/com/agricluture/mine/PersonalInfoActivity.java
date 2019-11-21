package com.agricluture.mine;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agricluture.base.BaseActivity;
import com.agricluture.main.bean.UserDetailBean;
import com.agricluture.mine.bean.HeadEvent;
import com.agricluture.mine.bean.PersonalEvent;
import com.agricluture.utils.AddressUtils;
import com.agricluture.utils.GlideUtils;
import com.agricluture.utils.JumperUtils;
import com.agricluture.utils.MatisseUtils;
import com.agricluture.view.DialogCustom;
import com.agricluture.view.FourDialogCustom;
import com.agriculture.R;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ObjectUtils;
import com.gcssloop.widget.RCImageView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.compress.FileUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PersonalInfoActivity extends BaseActivity {

    private static final int PERSON_REQUEST_CODE = 2222;
    @BindView(R.id.person_iv_head)
    RCImageView personIvHead;
    @BindView(R.id.person_tv_name)
    TextView personTvName;
    @BindView(R.id.person_tv_sex)
    TextView personTvSex;
    @BindView(R.id.person_tv_contact)
    TextView personTvContact;
    @BindView(R.id.person_tv_company)
    TextView personTvCompany;
    @BindView(R.id.person_tv_area)
    TextView personTvArea;
    @BindView(R.id.person_tv_address)
    TextView personTvAddress;
    @BindView(R.id.person_rly_address)
    RelativeLayout personRlyAddress;

    private String urlHead;
    private Bundle bundle;
    private  AddressUtils addressUtils;

    private UserDetailBean userDetailBean;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void initViews() {

        initTitleBar(R.string.personal_info);

        userDetailBean =getIntent().getParcelableExtra("bean");
        bundle = new Bundle();
        addressUtils =new AddressUtils();
        addressUtils.initJsonData(activity);

        GlideUtils.setImageSrc(activity,personIvHead,userDetailBean.getStrpicturepath());
        personTvName.setText(userDetailBean.getStrrealname());
        if("1".equals(userDetailBean.getBitsex())){
            personTvSex.setText("男");
        }else{
            personTvSex.setText("女");
        }
        personTvContact.setText(userDetailBean.getStrmobilephone());
        personTvCompany.setText(userDetailBean.getStrorgname());
        personTvArea.setText(userDetailBean.getStrliveaddress());
        personTvAddress.setText(userDetailBean.getStrliveaddress());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @OnClick({R.id.person_rly_head, R.id.person_rly_name, R.id.person_rly_sex, R.id.person_rly_contact, R.id.person_rly_company, R.id.person_rly_area, R.id.person_rly_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_rly_head:

                final DialogCustom bottomDialog = new DialogCustom(this, R.layout.dialog_three_item_bottom, "bottom");
                bottomDialog.findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPics(true);
                        if (bottomDialog != null) {
                            bottomDialog.dismiss();
                        }
                    }
                });
                bottomDialog.findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPics(false);
                        if (bottomDialog != null) {
                            bottomDialog.dismiss();
                        }
                    }
                });
                bottomDialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (bottomDialog != null) {
                            bottomDialog.dismiss();
                        }
                    }
                });
                bottomDialog.show();
                break;
            case R.id.person_rly_name:
                bundle.putInt("flag",1);
                JumperUtils.JumpTo(activity,NamePhoneCompanyActivity.class,bundle);
                break;
            case R.id.person_rly_sex:

                final FourDialogCustom genderBottomDialog1 = new FourDialogCustom(this, R.layout.dialog_sex_item_bottom, "bottom");
                genderBottomDialog1.setOnClickListener(new FourDialogCustom.OnClickListener() {

                    @Override
                    public void onClick(int position, final String str) {
                        personTvSex.setText(str);
                    }
                    @Override
                    public void onCancel() {
                    }
                });
                genderBottomDialog1.show();

                break;
            case R.id.person_rly_contact:
                bundle.putInt("flag",2);
                JumperUtils.JumpTo(activity,NamePhoneCompanyActivity.class,bundle);
                break;
            case R.id.person_rly_company:
                bundle.putInt("flag",3);
                JumperUtils.JumpTo(activity,NamePhoneCompanyActivity.class,bundle);
                break;
            case R.id.person_rly_area:
                showPickerView();
                break;
            case R.id.person_rly_address:
                bundle.putInt("flag",4);
                JumperUtils.JumpTo(activity,NamePhoneCompanyActivity.class,bundle);
                break;
                default:
        }
    }
    /**
     * 动态调取拍照权限
     * @param onlyCamera boolean是否调取
     */
    private void getPics(final boolean onlyCamera) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {

                            if (onlyCamera) {
                                MatisseUtils.getCamera(activity, PERSON_REQUEST_CODE);
                            } else {
                                MatisseUtils.getSingleImage(activity, PERSON_REQUEST_CODE);
                            }

                        } else {
                            Toast.makeText(activity, "获取权限失败！", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERSON_REQUEST_CODE&&resultCode == RESULT_OK) {
            File file = FileUtil.getFileByPath(Matisse.obtainPathResult(data).get(0));
            final File compressFile = MatisseUtils.compressToFile(activity, file);
            urlHead = compressFile.getPath();
            GlideUtils.setImageSrc(activity,personIvHead,urlHead);
        }
    }
    @Override
    public void onDestroy() {

        if(!ObjectUtils.isEmpty(urlHead)){
            EventBus.getDefault().post(new HeadEvent(urlHead,true));
        }
        super.onDestroy();


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(PersonalEvent personalEvent) {
        if(personalEvent.getFlags() ==1){
            personTvName.setText(personalEvent.contents);
        }else if(personalEvent.getFlags() ==2){
            personTvContact.setText(personalEvent.contents);
        }else if(personalEvent.getFlags() ==3){
            personTvCompany.setText(personalEvent.contents);
        }else{
            personTvAddress.setText(personalEvent.contents);
        }
    }
    private void showPickerView() {// 弹出选择器（省市区三级联动）
        OptionsPickerView pvOptions = new OptionsPickerBuilder(activity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                personTvArea.setText(addressUtils.options1Items.get(options1).getPickerViewText() + "  "
                        + addressUtils.options2Items.get(options1).get(options2) + "  "
                        + addressUtils.options3Items.get(options1).get(options2).get(options3));

            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(addressUtils.options1Items, addressUtils.options2Items, addressUtils.options3Items);//三级选择器
        pvOptions.show();
    }
}
