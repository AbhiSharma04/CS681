package edu.umb.cs681.hw11.fs;


import java.time.LocalDateTime;

public class TestFixtureInitializer {

    public static FileSystem createFS(){
        //root element initialized
        Directory prjRoot = new Directory (null,"prjRoot",0, LocalDateTime.now());
        Directory src = new Directory (prjRoot,"src",0, LocalDateTime.now());
        Directory lib = new Directory (prjRoot,"lib",0, LocalDateTime.now());
        Directory test = new Directory (prjRoot,"test",0, LocalDateTime.now());
        File x = new File (prjRoot,"x",200,LocalDateTime.now());
        Link y= new Link (prjRoot,"y",0,LocalDateTime.now(),src);
        // we have initialized all directories and files whose parent is root element
        // we will create children of src directory
        File a = new File (src,"a",414,LocalDateTime.now());
        File b = new File (src,"b",30,LocalDateTime.now());

        // we will create children of lib directory
        File c = new File (lib,"c",500,LocalDateTime.now());

        // we will create children of test directory
        Directory srcT = new Directory (test,"src",0, LocalDateTime.now());
        File d = new File (srcT,"d",100,LocalDateTime.now());

        prjRoot.appendChild(src);
        prjRoot.appendChild(lib);
        prjRoot.appendChild(test);
        prjRoot.appendChild(x);
        prjRoot.appendChild(y);

        src.appendChild(a);
        src.appendChild(b);

        lib.appendChild(c);

        test.appendChild(srcT);
        srcT.appendChild(d);








        Directory Root = new Directory (null,"Root",0, LocalDateTime.now());
        Directory g = new Directory (Root,"g",0, LocalDateTime.now());
        Directory h = new Directory (Root,"h",0, LocalDateTime.now());
        Directory i = new Directory (Root,"i",0, LocalDateTime.now());
        File rootfile = new File (Root,"rootfile",200,LocalDateTime.now());
        Link rootlink= new Link (Root,"rootlink",0,LocalDateTime.now(),rootfile);

        File g_file = new File (g,"g_file",414,LocalDateTime.now());
        File g_file2 = new File (g,"g_file2",30,LocalDateTime.now());


        File h_file = new File (h,"c",500,LocalDateTime.now());


        Directory i_test = new Directory (i,"i_test",0, LocalDateTime.now());
        File i_file = new File (i_test,"i_file",100,LocalDateTime.now());

        Root.appendChild(g);
        Root.appendChild(h);
        Root.appendChild(i);
        Root.appendChild(rootfile);
        Root.appendChild(rootlink);

        g.appendChild(g_file);
        g.appendChild(g_file2);

        h.appendChild(h_file);

        i.appendChild(i_test);
        i_test.appendChild(i_file);









        Directory User = new Directory (null,"User",0, LocalDateTime.now());
        Directory local = new Directory (User,"local",0, LocalDateTime.now());
        Directory admin = new Directory (User,"admin",0, LocalDateTime.now());
        Directory abhi = new Directory (User,"abhi",0, LocalDateTime.now());
        File localfile = new File (local,"localfile",200,LocalDateTime.now());
        Link locallink= new Link (local,"locallink",0,LocalDateTime.now(),local);

        File admin_file = new File (admin,"admin_file",414,LocalDateTime.now());
        File admin_file2 = new File (admin,"admin_file2",30,LocalDateTime.now());


        File abhi_file = new File (abhi,"abhi_file",500,LocalDateTime.now());


        Directory abhi_pics = new Directory (abhi,"abhi_pics",0, LocalDateTime.now());
        File pics = new File (abhi_pics,"pics",100,LocalDateTime.now());

        User.appendChild(local);
        User.appendChild(admin);
        User.appendChild(abhi);
        local.appendChild(localfile);
        local.appendChild(locallink);

        admin.appendChild(admin_file);
        admin.appendChild(admin_file2);

        abhi.appendChild(abhi_file);

        abhi.appendChild(abhi_pics);
        abhi_pics.appendChild(pics);




        FileSystem fs= FileSystem.getFileSystem();
        fs.appendRootDir(prjRoot);
        fs.appendRootDir (Root);
        fs.appendRootDir (User);

        return fs;

    }
}
