/*
 * package com.credochain.usermanagement.service;
 * 
 * import static org.mockito.Mockito.when;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import org.mockito.InjectMocks; import
 * org.mockito.Mock; import org.mockito.Mockito; import
 * org.mockito.MockitoAnnotations; import org.mockito.junit.MockitoJUnitRunner;
 * 
 * import com.credochain.commonservice.request.models.TriggerOtpRequest; import
 * com.credochain.usermanagement.constants.ConfigConstants; import
 * com.credochain.usermanagement.dao.OtpDao; import
 * com.credochain.usermanagement.dao.UserDao; import
 * com.credochain.usermanagement.dao.impl.CommonDao; import
 * com.credochain.usermanagement.service.helper.OtpServiceHelper; import
 * com.credochain.usermanagement.service.helper.UserServiceHelper; import
 * com.credochain.usermanagement.utils.CommonUtils; import
 * com.credochain.usermanagement.utils.Utils;
 * 
 * @RunWith(MockitoJUnitRunner.class) public class OtpServiceImplTest {
 * 
 * @InjectMocks OtpService otpService;
 * 
 * @InjectMocks private OtpServiceHelper otpServiceHelper;
 * 
 * @Mock private UserDao userDao;
 * 
 * @InjectMocks private CommonUtils commonUtils;
 * 
 * @InjectMocks private ConfigConstants configConstants;
 * 
 * @Mock private OtpDao otpDao;
 * 
 * @InjectMocks private UserServiceHelper userServiceHelper;
 * 
 * @Mock private CommonDao commonDao;
 * 
 * @InjectMocks private Utils utils;
 * 
 * @Before public void init() { MockitoAnnotations.initMocks(this); }
 * 
 * @Test public void triggerOtpTest() { //
 * when(otpServiceHelper.validateTriggerOtpRequest(Mockito.any())).thenReturn(
 * null); when(userDao.findUserByMobileNumber(Mockito.any())).thenReturn(null);
 * otpService.triggerOtp(new TriggerOtpRequest("7467467647"));
 * 
 * }
 * 
 * }
 */