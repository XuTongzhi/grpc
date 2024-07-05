package function;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class SshFileDownloader {
    public static void main(String[] args) {
        String host = "106.15.181.252";
        String username = "root";
        String privateKeyFile = "C:\\Users\\Administrator\\Desktop\\fs_alex\\deploy.pem"; // 私钥文件路径

        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            jsch.addIdentity(privateKeyFile);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session = jsch.getSession(username, host);
            session.setConfig(config);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            String[] remoteFiles = {
                    "/root/.acme.sh/*.fusionadtech.com/*.fusionadtech.com.cer",
                    "/root/.acme.sh/*.fusionadtech.com/*.fusionadtech.com.key",
                    "/root/.acme.sh/*.hypersdesk.com/*.hypersdesk.com.cer",
                    "/root/.acme.sh/*.hypersdesk.com/*.hypersdesk.com.key"
            };

            List<String> list = new ArrayList<>();

            for (String remoteFile : remoteFiles) {
                InputStream inputStream = channelSftp.get(remoteFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder contentBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    contentBuilder.append(line).append("\n");
                }
                list.add(contentBuilder.toString());
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channelSftp != null) {
                channelSftp.exit();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }
}