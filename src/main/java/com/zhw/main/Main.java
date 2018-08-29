package com.zhw.main;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author: zhangHaiWen
 * @date : 2018/4/2 0002 上午 8:18
 * @DESC :
 * {java.runtime.name=Java(TM) SE Runtime Environment,
 * sun.boot.library.path=C:\Program Files\Java\jdk1.7.0_79\jre\bin,
 * java.vm.version=24.79-b02, java.vm.vendor=Oracle Corporation,
 * java.vendor.url=http://java.oracle.com/, path.separator=;,
 * idea.launcher.port=7533,
 * java.vm.name=Java HotSpot(TM) 64-Bit Server VM,
 * file.encoding.pkg=sun.io,
 * user.country=CN,
 * user.script=,
 * sun.java.launcher=SUN_STANDARD,
 * sun.os.patch.level=Service Pack 1,
 * java.vm.specification.name=Java Virtual Machine Specification,
 * user.dir=E:\zhanghaiwen\DebboDemo,
 * java.runtime.version=1.7.0_79-b15,
 * java.awt.graphicsenv=sun.awt.Win32GraphicsEnvironment,
 * java.endorsed.dirs=C:\Program Files\Java\jdk1.7.0_79\jre\lib\endorsed,
 * os.arch=amd64,
 * java.io.tmpdir=C:\Users\ADMINI~1\AppData\Local\Temp\,
 * line.separator=,
 * java.vm.specification.vendor=Oracle Corporation,
 * user.variant=,
 * os.name=Windows 7,
 * sun.jnu.encoding=GBK,
 * java.library.path=C:\Program Files\Java\jdk1.7.0_79\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\Windows7Master;C:\Program Files\Java\jdk1.7.0_79\bin;C:\Program Files\Java\jdk1.7.0_79\jre\bin;D:\kaifa\apache-maven-3.3.9\bin;D:\Program Files\nodejs\;C:\Program Files (x86)\NVIDIA Corporation\PhysX\Common;E:\zhanghaiwen\mysql\mysql-5.7.21-winx64\bin;D:\kaifa\Git\cmd;C:\Users\Administrator\AppData\Roaming\npm;., java.specification.name=Java Platform API Specification, java.class.version=51.0, sun.management.compiler=HotSpot 64-Bit Tiered Compilers, os.version=6.1, user.home=C:\Users\Administrator, user.timezone=, java.awt.printerjob=sun.awt.windows.WPrinterJob, file.encoding=UTF-8, idea.launcher.bin.path=D:\Program Files (x86)\JetBrains\IntelliJ IDEA 2016.3.1\bin, java.specification.version=1.7, java.class.path=C:\Program Files\Java\jdk1.7.0_79\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jce.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jfxrt.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\resources.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\rt.jar;E:\zhanghaiwen\DebboDemo\target\classes;D:\xxlc_code\xiaoche_2_maven\org\springframework\spring-core\4.1.7.RELEASE\spring-core-4.1.7.RELEASE.jar;D:\xxlc_code\xiaoche_2_maven\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;D:\xxlc_code\xiaoche_2_maven\org\springframework\spring-beans\4.1.7.RELEASE\spring-beans-4.1.7.RELEASE.jar;D:\xxlc_code\xiaoche_2_maven\org\springframework\spring-tx\4.1.7.RELEASE\spring-tx-4.1.7.RELEASE.jar;D:\xxlc_code\xiaoche_2_maven\org\springframework\spring-context\4.1.7.RELEASE\spring-context-4.1.7.RELEASE.jar;D:\xxlc_code\xiaoche_2_maven\org\springframework\spring-aop\4.1.7.RELEASE\spring-aop-4.1.7.RELEASE.jar;D:\xxlc_code\xiaoche_2_maven\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;D:\xxlc_code\xiaoche_2_maven\org\springframework\spring-expression\4.1.7.RELEASE\spring-expression-4.1.7.RELEASE.jar;D:\xxlc_code\xiaoche_2_maven\org\springframework\spring-context-support\4.1.7.RELEASE\spring-context-support-4.1.7.RELEASE.jar;D:\xxlc_code\xiaoche_2_maven\org\springframework\spring-web\4.1.7.RELEASE\spring-web-4.1.7.RELEASE.jar;D:\xxlc_code\xiaoche_2_maven\org\springframework\spring-webmvc\4.1.7.RELEASE\spring-webmvc-4.1.7.RELEASE.jar;D:\xxlc_code\xiaoche_2_maven\com\alibaba\dubbo\2.5.3\dubbo-2.5.3.jar;D:\xxlc_code\xiaoche_2_maven\org\springframework\spring\2.5.6.SEC03\spring-2.5.6.SEC03.jar;D:\xxlc_code\xiaoche_2_maven\org\javassist\javassist\3.15.0-GA\javassist-3.15.0-GA.jar;D:\xxlc_code\xiaoche_2_maven\org\jboss\netty\netty\3.2.5.Final\netty-3.2.5.Final.jar;D:\Program Files (x86)\JetBrains\IntelliJ IDEA 2016.3.1\lib\idea_rt.jar, user.name=Administrator, java.vm.specification.version=1.7, sun.java.command=com.intellij.rt.execution.application.AppMain com.zhw.main.Main, java.home=C:\Program Files\Java\jdk1.7.0_79\jre, sun.arch.data.model=64, user.language=zh, java.specification.vendor=Oracle Corporation, awt.toolkit=sun.awt.windows.WToolkit, java.vm.info=mixed mode, java.version=1.7.0_79, java.ext.dirs=C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext;C:\Windows\Sun\Java\lib\ext, sun.boot.class.path=C:\Program Files\Java\jdk1.7.0_79\jre\lib\resources.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\rt.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\sunrsasign.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jce.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.7.0_79\jre\classes,
 * java.vendor=Oracle Corporation,
 * file.separator=\,
 * java.vendor.url.bug=http://bugreport.sun.com/bugreport/,
 * sun.io.unicode.encoding=UnicodeLittle,
 * sun.cpu.endian=little,
 * sun.desktop=windows,
 * sun.cpu.isalist=amd64}
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String str1 = "asd";
        String str2 = "asd";
        System.out.println(str1.intern()==str2);

    }

    public static Boolean checkPassword(String password){
        //正则校验密码包含一个大写，一个小写字母，长度6-16位
        boolean regex = Pattern.matches("^(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{6,16}$", password);
        //正则校验密码不包含特殊字符
        boolean regex1 = Pattern.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", password);
        return regex&&regex1;
    }
}

class father{
    public void fsay(){

    }
}
class chilend extends father{
    public void csay(){

    }
}