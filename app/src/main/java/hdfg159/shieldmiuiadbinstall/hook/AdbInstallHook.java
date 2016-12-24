package hdfg159.shieldmiuiadbinstall.hook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Project:Shield MIUI ADB Install
 * Package:hdfg159.shieldmiuiadbinstall.hook
 * Created by hdfg159 on 2016/12/24 10:19.
 */
public class AdbInstallHook implements IXposedHookLoadPackage {
    private static final String MIUI_SECURITY_CENTER_PACKAGENAME = "com.miui.securitycenter";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (isEqualsSecurityCenterPackageName(loadPackageParam)) {
            findAndHookMethod("com.miui.permcenter.install.a", loadPackageParam.classLoader, "isEnabled", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    return false;
                }
            });
        }
    }

    private boolean isEqualsSecurityCenterPackageName(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        return loadPackageParam.packageName.equals(MIUI_SECURITY_CENTER_PACKAGENAME);
    }
}
