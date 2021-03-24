# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# 表示混淆时不使用大小写混合类名
# -dontusemixedcaseclassnames
# 表示不跳过library中的非public的类
#-dontskipnonpubliclibraryclasses
# 打印混淆的详细信息
-verbose
# 不混淆第三方引用的库
-dontskipnonpubliclibraryclasses
#保证是独立的jar,没有任何项目引用,如果不写就会认为我们所有的代码是无用的,从而把所有的代码压缩掉,导出一个空的jar
-dontshrink

-dontoptimize
# 表示不进行校验,这个校验作用 在java平台上的
-dontpreverify

 # 保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepattributes Exceptions

#-keep public class * extends android.app.Activity
-keep public class * extends android.view.View
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference


-keep class com.hjhl.animalMatching_SDK.api.**{*;}
-keep class com.hjhl.animalMatching_SDK.common.model.**{*;}
# -keep class com.hjhl.animalMatching_SDK.common.adapter.GVAdapter


-keepattributes *Annotation*

#联网不被混淆
-keepattributes Signature
-keep class com.squareup.okhttp.** { *;}
-dontwarn com.squareup.okhttp.**
-dontwarn okio.**


# Gson
-keepattributes EnclosingMethod
-keep class com.google.gson.** {*;}
-keep class com.google.**{*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }

-libraryjars libs/gson-2.8.5.jar
-libraryjars libs/okhttp-4.9.0.jar