# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#These rules suppress warnings about missing classes or members from specific libraries.
#This can help clean up your build logs.
-dontwarn org.bouncycastle.jsse.**
-dontwarn org.conscrypt.*
-dontwarn org.openjsse.**
# Glide optionally depends on AppCompat
-dontwarn androidx.appcompat.**

#This keeps the CREATOR field required for Parcelable classes, preserving the names for serialization.
-keepnames class * implements android.os.Parcelable {
   public static final ** CREATOR;
}

#These rules keep various attributes in your classes, which can be necessary for reflection
# or other frameworks that rely on annotations or method signatures.
# attributes
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes EnclosingMethod
-keepattributes SourceFile,LineNumberTable

#This keeps the necessary methods and fields for any classes that extend Enum,
#which is crucial for their correct functionality
-keepclassmembers class * extends java.lang.Enum {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}