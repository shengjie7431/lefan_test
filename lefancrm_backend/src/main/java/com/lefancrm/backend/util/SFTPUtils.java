package com.lefancrm.backend.util;
        import java.awt.*;
        import java.awt.image.BufferedImage;
        import java.io.*;
        import java.nio.file.FileSystemNotFoundException;
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;
        import java.util.Properties;
        import java.util.Vector;

        import com.jcraft.jsch.*;
        import net.coobird.thumbnailator.Thumbnails;
        import org.apache.log4j.Logger;

        import javax.imageio.ImageIO;

/**
 * Created by Jani on 2019/3/7.
 */
public class SFTPUtils {
    private static Logger log = Logger.getLogger(SFTPUtils.class.getName());

    private String host;//服务器连接ip
    private String username;//用户名
    private String password;//密码
    private int port = 22;//端口号
    private ChannelSftp sftp = null;
    private Session sshSession = null;

    public SFTPUtils(){}

    public SFTPUtils(String host, int port, String username, String password)
    {
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public SFTPUtils(String host, String username, String password)
    {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    /**
     * 通过SFTP连接服务器
     */
    public void connect()
    {
        try
        {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            sshSession = jsch.getSession(username, host, port);
            if (log.isInfoEnabled())
            {
                log.info("Session created.");
            }
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            if (log.isInfoEnabled())
            {
                log.info("Session connected.");
            }
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            if (log.isInfoEnabled())
            {
                log.info("Opening Channel.");
            }
            sftp = (ChannelSftp) channel;
            if (log.isInfoEnabled())
            {
                log.info("Connected to " + host + ".");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    public void disconnect()
    {
        if (this.sftp != null)
        {
            if (this.sftp.isConnected())
            {
                this.sftp.disconnect();
                if (log.isInfoEnabled())
                {
                    log.info("sftp is closed already");
                }
            }
        }
        if (this.sshSession != null)
        {
            if (this.sshSession.isConnected())
            {
                this.sshSession.disconnect();
                if (log.isInfoEnabled())
                {
                    log.info("sshSession is closed already");
                }
            }
        }
    }

    /**
     * 批量下载文件
     * @param ：远程下载目录(以路径符号结束,可以为相对路径eg:/assess/sftp/jiesuan_2/2014/)
     * @param localPath：本地保存目录(以路径符号结束,D:\Duansha\sftp\)
     * @param fileFormat：下载文件格式(以特定字符开头,为空不做检验)
     * @param fileEndFormat：下载文件格式(文件格式)
     * @param del：下载后是否删除sftp文件
     * @return
     */
    public List<String> batchDownLoadFile(String remotePath, String localPath,
                                          String fileFormat, String fileEndFormat, boolean del)
    {
        List<String> filenames = new ArrayList<String>();
        try
        {
            // connect();
            Vector v = listFiles(remotePath);
            // sftp.cd(remotePath);
            if (v.size() > 0)
            {
                System.out.println("本次处理文件个数不为零,开始下载...fileSize=" + v.size());
                Iterator it = v.iterator();
                while (it.hasNext())
                {
                    ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) it.next();

                    String filename = entry.getFilename();
                    SftpATTRS attrs = entry.getAttrs();
                    if (!attrs.isDir())
                    {
                        boolean flag = false;
                        String localFileName = localPath + filename;
                        fileFormat = fileFormat == null ? "" : fileFormat
                                .trim();
                        fileEndFormat = fileEndFormat == null ? ""
                                : fileEndFormat.trim();
                        // 三种情况
                        if (fileFormat.length() > 0 && fileEndFormat.length() > 0)
                        {
                            if (filename.startsWith(fileFormat) && filename.endsWith(fileEndFormat))
                            {
                                flag = downloadFile(remotePath, filename,localPath, filename);
                                if (flag)
                                {
                                    filenames.add(localFileName);
                                    if (flag && del)
                                    {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        }
                        else if (fileFormat.length() > 0 && "".equals(fileEndFormat))
                        {
                            if (filename.startsWith(fileFormat))
                            {
                                flag = downloadFile(remotePath, filename, localPath, filename);
                                if (flag)
                                {
                                    filenames.add(localFileName);
                                    if (flag && del)
                                    {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        }
                        else if (fileEndFormat.length() > 0 && "".equals(fileFormat))
                        {
                            if (filename.endsWith(fileEndFormat))
                            {
                                flag = downloadFile(remotePath, filename,localPath, filename);
                                if (flag)
                                {
                                    filenames.add(localFileName);
                                    if (flag && del)
                                    {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        }
                        else
                        {
                            flag = downloadFile(remotePath, filename,localPath, filename);
                            if (flag)
                            {
                                filenames.add(localFileName);
                                if (flag && del)
                                {
                                    deleteSFTP(remotePath, filename);
                                }
                            }
                        }
                    }
                }
            }
            if (log.isInfoEnabled())
            {
                log.info("download file is success:remotePath=" + remotePath
                        + "and localPath=" + localPath + ",file size is"
                        + v.size());
            }
        }
        catch (SftpException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // this.disconnect();
        }
        return filenames;
    }

    /**
     * 下载单个文件
     * @param ：远程下载目录(以路径符号结束)
     * @param remoteFileName：下载文件名
     * @param localPath：本地保存目录(以路径符号结束)
     * @param localFileName：保存文件名
     * @return
     */
    public boolean downloadFile(String remotePath, String remoteFileName,String localPath, String localFileName)
    {
        FileOutputStream fieloutput = null;
        try
        {
            // sftp.cd(remotePath);
            File file = new File(localPath + localFileName);
            // mkdirs(localPath + localFileName);
            fieloutput = new FileOutputStream(file);
            sftp.get(remotePath + remoteFileName, fieloutput);
            if (log.isInfoEnabled())
            {
                log.info("===DownloadFile:" + remoteFileName + " success from sftp.");
            }
            sftp.disconnect();
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SftpException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != fieloutput)
            {
                try
                {
                    fieloutput.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean downloadFile(String remotePath, String remoteFileName,String localPath, String localFileName,Boolean del)
    {
        FileOutputStream fieloutput = null;
        try
        {
            // sftp.cd(remotePath);
            File file = new File(localPath + localFileName);
            // mkdirs(localPath + localFileName);
            fieloutput = new FileOutputStream(file);
            sftp.get(remotePath + remoteFileName, fieloutput);
            if (del){
                deleteSFTP(remotePath, remoteFileName);
            }
            if (log.isInfoEnabled())
            {
                log.info("===DownloadFile:" + remoteFileName + " success from sftp.");
            }
            sftp.disconnect();
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SftpException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != fieloutput)
            {
                try
                {
                    fieloutput.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean uploadFile(String remotePath, String remoteFileName,InputStream in)
    {
        try
        {
            createDir(remotePath);
            sftp.put(in, remoteFileName);
            sftp.disconnect();
            return true;
        }
        catch (FileSystemNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SftpException e)
        {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 上传单个文件
     * @param remotePath：远程保存目录
     * @param remoteFileName：保存文件名
     * @param localPath：本地上传目录(以路径符号结束)
     * @param localFileName：上传的文件名
     * @return
     */
    public boolean uploadFile(String remotePath, String remoteFileName,String localPath, String localFileName)
    {
        FileInputStream in = null;
        try
        {
            createDir(remotePath);
            File file = new File(localPath + localFileName);
            in = new FileInputStream(file);
            sftp.put(in, remoteFileName);
            sftp.disconnect();
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SftpException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 批量上传文件
     * @param remotePath：远程保存目录
     * @param localPath：本地上传目录(以路径符号结束)
     * @param del：上传后是否删除本地文件
     * @return
     */
    public boolean bacthUploadFile(String remotePath, String localPath,
                                   boolean del)
    {
        try
        {
            connect();
            File file = new File(localPath);
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isFile()
                        && files[i].getName().indexOf("bak") == -1)
                {
                    if (this.uploadFile(remotePath, files[i].getName(),
                            localPath, files[i].getName())
                            && del)
                    {
                        deleteFile(localPath + files[i].getName());
                    }
                }
            }
            if (log.isInfoEnabled())
            {
                log.info("upload file is success:remotePath=" + remotePath
                        + "and localPath=" + localPath + ",file size is "
                        + files.length);
            }
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            this.disconnect();
        }
        return false;

    }

    /**
     * 删除本地文件
     * @param filePath
     * @return
     */
    public boolean deleteFile(String filePath)
    {
        File file = new File(filePath);
        if (!file.exists())
        {
            return false;
        }

        if (!file.isFile())
        {
            return false;
        }
        boolean rs = file.delete();
        if (rs && log.isInfoEnabled())
        {
            log.info("delete file success from local.");
        }
        return rs;
    }

    /**
     * 创建目录
     * @param createpath
     * @return
     */
    public boolean createDir(String createpath)
    {
        try
        {
            if (isDirExist(createpath))
            {
                this.sftp.cd(createpath);
                return true;
            }
            String pathArry[] = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry)
            {
                if (path.equals(""))
                {
                    continue;
                }
                filePath.append(path + "/");
                if (isDirExist(filePath.toString()))
                {
                    sftp.cd(filePath.toString());
                }
                else
                {
                    // 建立目录
                    sftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    sftp.cd(filePath.toString());
                }

            }
            this.sftp.cd(createpath);
            return true;
        }
        catch (SftpException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断目录是否存在
     * @param directory
     * @return
     */
    public boolean isDirExist(String directory)
    {
        boolean isDirExistFlag = false;
        try
        {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        }
        catch (Exception e)
        {
            if (e.getMessage().toLowerCase().equals("no such file"))
            {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

    /**
     * 删除stfp文件
     * @param directory：要删除文件所在目录
     * @param deleteFile：要删除的文件
     * @param
     */
    public void deleteSFTP(String directory, String deleteFile)
    {
        try
        {
            // sftp.cd(directory);
            sftp.rm(directory + deleteFile);
            if (log.isInfoEnabled())
            {
                log.info("delete file success from sftp.");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 如果目录不存在就创建目录
     * @param path
     */
    public void mkdirs(String path)
    {
        File f = new File(path);

        String fs = f.getParent();

        f = new File(fs);

        if (!f.exists())
        {
            f.mkdirs();
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory：要列出的目录
     * @param
     * @return
     * @throws SftpException
     */
    public Vector listFiles(String directory) throws SftpException
    {
        return sftp.ls(directory);
    }

    public Boolean folder(String directory)throws SftpException
    {
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            if (sftpATTRS != null){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
    /**
     * 判断目录是否存在
     * @param directory
     * @return
     * @throws SftpException
     */
    public static Boolean existsFolder(String surveyUploadIp,String directory) throws SftpException
    {
        SFTPUtils sftp = new SFTPUtils(surveyUploadIp, "root", "shlefan.com123");
        sftp.connect();
        return sftp.folder(directory);
    }

    public static Vector listFiles(String surveyUploadIp,String directory) throws SftpException
    {
        SFTPUtils sftp = new SFTPUtils(surveyUploadIp, "root", "shlefan.com123");
        sftp.connect();
        return sftp.listFiles(directory);
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public ChannelSftp getSftp()
    {
        return sftp;
    }

    public void setSftp(ChannelSftp sftp)
    {
        this.sftp = sftp;
    }


    /**测试*/
    public static void main(String[] args)
    {
        SFTPUtils sftp = null;
        // 本地存放地址
        String localPath = "F:/ftp/";
        // Sftp下载路径
        String sftpPath = "/file/你好/";
        List<String> filePathList = new ArrayList<String>();
        try
        {
            sftp = new SFTPUtils("123.60.40.210", "root", "Lefan.com%+^lfhw=1020");
            sftp.connect();
            String filePath = "/mnt/sftp/files/test/ddr/cno/cwt1331570760897909/direction/医疗调查/对的/dd/ss";
//            Vector vector = sftp.listFiles("/mnt/sftp/files/test/ddr/cno/cwt1331570760897909/direction/医疗调查/对的/dd/ss");
//            if (vector.size() > 0 ){
//                Iterator iterator = vector.iterator();
//                while (iterator.hasNext()) {
//                    ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) iterator.next();
//                    String filename = entry.getFilename();
//                    SftpATTRS attrs = entry.getAttrs();
//                    System.out.println(entry.getLongname());
//                    System.out.println(filename);
//                    System.out.println(attrs.isDir());
//                }
//            }

           // sftp.deleteSFTP(filePath,"");
            // 下载
//            sftp.batchDownLoadFile(sftpPath, localPath, "", "", false);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            sftp.disconnect();
        }
    }

    public static SFTPUtils getSFTPObj(){
        try {
            SFTPUtils sftp = null;
            sftp = new SFTPUtils("123.60.40.210", "sftp", "Lefan.com%+^lfhw=1020");
            sftp.connect();
            return sftp;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean upload(String surveyUploadIp,String surveyUploadPwd,String filePath,String fileName,InputStream inputStream) throws Exception{
        SFTPUtils sftp = new SFTPUtils(surveyUploadIp, "root", surveyUploadPwd);
        sftp.connect();
        return sftp.uploadFile(filePath,fileName,inputStream);
    }
    public static Boolean down(String filePath,String fileName,String localFilePath,String localFileName){
        SFTPUtils sftp = new SFTPUtils("123.60.40.210", "root", "Lefan.com%+^lfhw=1020");
        sftp.connect();
        return sftp.downloadFile(filePath,fileName,localFilePath,localFileName);
    }
    public static Boolean down(String filePath,String fileName,String localFilePath,String localFileName,Boolean del){
        SFTPUtils sftp = new SFTPUtils("123.60.40.210", "root", "Lefan.com%+^lfhw=1020");
        sftp.connect();
        return sftp.downloadFile(filePath,fileName,localFilePath,localFileName,del);
    }

    /**
     *  改变图片的大小到宽为size，然后高随着宽等比例变化
     * @param is  源文件输入流
     * @param surveyUploadIp  目标地址IP
     * @param filePath    目标路径
     * @param fileName    文件名
     * @return  boolean
     * @throws IOException
     */
    public static Boolean resizeImage(InputStream is,String surveyUploadIp,String surveyUploadPwd, String filePath, String fileName) throws Exception {

        BufferedImage prevImage = ImageIO.read(is);
        int size = 1024; //设置宽
        double width = prevImage.getWidth();
        double height = prevImage.getHeight();
        double percent = size/width;
        int newWidth =  width<=size?(int)width:(int)(width * percent);
        int newHeight = width<=size?(int)height:(int)(height * percent);
        BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", output);
        byte[] buff = output.toByteArray();
        InputStream in = new ByteArrayInputStream(buff);
        return upload(surveyUploadIp,surveyUploadPwd,filePath,fileName,in);
    }

    public static Boolean resizeImageNew(InputStream is, String surveyUploadIp,String surveyUploadPwd, String filePath, String fileName) throws Exception{
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Thumbnails.of(is).scale(0.5f).outputQuality(0.25f).toOutputStream(output);
        byte[] buff = output.toByteArray();
        InputStream in = new ByteArrayInputStream(buff);
        return upload(surveyUploadIp,surveyUploadPwd, filePath, fileName, in);
    }
}
