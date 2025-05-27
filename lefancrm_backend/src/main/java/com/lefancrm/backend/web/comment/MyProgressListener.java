package com.lefancrm.backend.web.comment;

import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MyProgressListener implements ProgressListener {
    private HttpSession httpSession;

    public MyProgressListener(HttpServletRequest request){
        httpSession = request.getSession();
    }
    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setItemNum(pItems);
        processInfo.setReadSize(pBytesRead);
        processInfo.setTotalSize(pContentLength);
        processInfo.setShow(pBytesRead + "/" + pContentLength + " byte");
        processInfo.setRate(Math.round(new Float(pBytesRead) / new Float(pContentLength)*100));
        System.out.println(processInfo.toString());
        httpSession.setAttribute("processInfo",processInfo);
    }
}
