package com.metain.web.service;

import com.metain.web.domain.Emp;
import com.metain.web.domain.EmpCert;
import com.metain.web.domain.ExperienceCert;
import com.metain.web.domain.RetireCert;
import com.metain.web.dto.AlarmDTO;
import com.metain.web.dto.MyCertDTO;
import com.metain.web.dto.MyVacDTO;
import com.metain.web.mapper.HrMapper;
import com.metain.web.mapper.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MyPageServiceImpl implements MyPageService{

    @Autowired
    private MyPageMapper myPageMapper;


    @Autowired
    private HrMapper hrMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public List<MyVacDTO> selectMyVacList(MyVacDTO myVacDTO) {
        List<MyVacDTO> list = myPageMapper.selectMyVacList(myVacDTO);
        if(list == null){
            return null;
        }
        return list;
    }

    @Override
    public List<MyVacDTO> myVacList(Long empId) {
        List<MyVacDTO> list =myPageMapper.myVacList(empId);
        return list;
    }

    //재직증명서 목록
    @Override
    public List<EmpCert> selectMyEmpCert(EmpCert empCert) {
        List<EmpCert> list = myPageMapper.selectMyEmpCert();

        // 리스트에 empCert 객체 추가
        list.add(empCert);

        return list;
    }


    //경력증명서 목록
    @Override
    public List<ExperienceCert> selectMyExperCert(ExperienceCert experienceCert) {
        List<ExperienceCert> list = myPageMapper.selectMyExperCert();
        return list;
    }

    //퇴직증명서 목록
    @Override
    public List<RetireCert> selectMyRetCert(RetireCert retireCert) {
        List<RetireCert> list = myPageMapper.selectMyRetCert();
        return list;
    }

    //다운로드할 증명서 파일이름가져오기
    @Override
    public String getCertFilename(Long certId, String certSort){


        String certFilename="";

        if (certSort.equals("A01")) {
            certFilename = myPageMapper.selectEmpCertFilename(certId);
        }else if (certSort.equals("A02")){
            certFilename = myPageMapper.selectExperCertFilename(certId);
        }else if (certSort.equals("A03")){
            certFilename = myPageMapper.selectRetireCertFilename(certId);
        }
        return certFilename;
    }

    public void updateIssueStatus(Long certId, String certSort){

        if (certSort.equals("A01") ) {
            myPageMapper.updateEmpIssueStatus(certId);
        }else if (certSort.equals("A02")){
            myPageMapper.updateExperIssueStatus(certId);
        }else if (certSort.equals("A03")){
            myPageMapper.updateRetireIssueStatus(certId);
        }else {
            System.out.println("Issue Status 업데이트할 정보 안들어옴 !");
        }
    }



    @Override
    public List<AlarmDTO> alarmList(Long empId) {
        List<AlarmDTO> list= myPageMapper.alarmList(empId);
        if(list==null){
            return null;
        }else return list;

    }

    @Override
    public void updateMy(Emp emp) {
        Emp dbemp = hrMapper.selectEmpInfo(emp.getEmpId());
        String encryptedPwd = bCryptPasswordEncoder.encode(emp.getEmpPwd());
        System.out.println(encryptedPwd);
        dbemp.setEmpPwd(encryptedPwd);
        System.out.println(encryptedPwd);
        dbemp.setEmpAddr(emp.getEmpAddr());
        dbemp.setEmpPhone(emp.getEmpPhone());
        dbemp.setEmpZipcode(emp.getEmpZipcode());
        dbemp.setEmpDetailAddr(emp.getEmpDetailAddr());

        myPageMapper.updateMyPage(dbemp);
    }
}
