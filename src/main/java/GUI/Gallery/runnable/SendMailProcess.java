package GUI.Gallery.runnable;

import GUI.Gallery.data.dao.baseDAO;
import GUI.Gallery.data.entity.Sender;
import GUI.Gallery.mail.SendEmails;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.Callable;

public class SendMailProcess implements Callable<String> {

    public String call() {
        SendEmails sendEmails = new SendEmails();
        String status = StringUtils.EMPTY;
        List<Sender> senderList = baseDAO.getSender();
        if (!senderList.isEmpty()) {
            status = sendEmails.send(senderList);
        }
        return status;
    }
}
