package ru.mgpy.Utils.Storage;

public class MyStorage {
  /*  private static final String TAG = "MyStorage";

    private static MyStorage instance;
    private Storage mStorage;
    private Context mContext;

    private MyStorage(Context context) {
        mStorage = SimpleStorage.getInternalStorage(context);
        this.mContext = context;
    }

    public static MyStorage init(Context context) {
        if (instance == null) {
            instance = new MyStorage(context);
        }
        return instance;
    }

    @Override
    public boolean isFileExist(String mpdu, String s) {
        return mStorage.isFileExist(mpdu, s);
    }

    @Override
    public void createFile(Response<ResponseBody> response, String nameFile) {
        try {
            if (!mStorage.isDirectoryExists("mdpu")) {
                mStorage.createDirectory("mdpu");
            }
            mStorage.createFile("mdpu", nameFile, response.body().bytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readFile(String dir, String fileName) {
        return readFromFile(dir, fileName);
    }

    @Override
    public String readAssetsFile(String name) {
        String tContents = "";
        try {
            InputStream stream = mContext.getAssets().open(name);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tContents;
    }

    @Override
    public String readFromFile(String dir, String name) {
        String ret = "";
        try {
            FileInputStream inputStream = new FileInputStream(getInternalFile(dir, name));
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }

            inputStream.close();
            ret = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public File getInternalFile(String directoryName, String fileName) {
        String path = buildPath(directoryName, fileName);
        return new File(path);
    }

    @Override
    public String buildPath(String directoryName, String fileName) {
        String path = mContext.getFilesDir().getAbsolutePath();
        path = path + File.separator + directoryName + File.separator + fileName;
        return path;
    }

    @Override
    public void deleteDir(String dir) {
        mStorage.deleteDirectory(dir);
    }*/
}
