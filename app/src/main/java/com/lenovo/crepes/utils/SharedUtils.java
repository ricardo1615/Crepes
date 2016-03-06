package com.lenovo.crepes.utils;

import android.content.Context;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;

/**
 * Created by Lenovo on 2015/11/12.
 */
public class SharedUtils {
    /**
     * @param mController
     * @param context
     */
    private void showShare(UMSocialService mController, Context context) {
        // 设置分享内容
        mController.setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能，http://www.umeng.com/social");
// 设置分享图片, 参数2为图片的url地址
        mController.setShareMedia(new UMImage(context, "http://www.umeng.com/images/pic/banner_module_social.png"));
        mController.setAppWebSite(SHARE_MEDIA.RENREN, "http://www.umeng.com/social");
// 设置分享图片，参数2为本地图片的资源引用
//mController.setShareMedia(new UMImage(getActivity(), R.drawable.icon));
// 设置分享图片，参数2为本地图片的路径(绝对路径)
//mController.setShareMedia(new UMImage(getActivity(),
//                                BitmapFactory.decodeFile("/mnt/sdcard/icon.png")));

// 设置分享音乐
//UMusic uMusic = new UMusic("http://sns.whalecloud.com/test_music.mp3");
//uMusic.setAuthor("GuGu");
//uMusic.setTitle("天籁之音");
// 设置音乐缩略图
//uMusic.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
//mController.setShareMedia(uMusic);

// 设置分享视频
//UMVideo umVideo = new UMVideo(
//          "http://v.youku.com/v_show/id_XNTE5ODAwMDM2.html?f=19001023");
// 设置视频缩略图
//umVideo.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
//umVideo.setTitle("友盟社会化分享!");
//mController.setShareMedia(umVideo);
    }
}
