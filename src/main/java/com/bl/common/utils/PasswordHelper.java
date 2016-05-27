package com.bl.common.utils;

public class PasswordHelper {

    private String algorithmName = "md5";
    private int hashIterations = 2;


    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public String encryptPassword(String salt,String pwd,String userName) {


//        return  new SimpleHash(
//                algorithmName,
//                pwd,
//                ByteSource.Util.bytes(userName+salt),
//                hashIterations).toHex();
    	return null;

    }
    
    public static void main(String[] args) {

		PasswordHelper h = new PasswordHelper();
		System.out.println(h.encryptPassword("8d78869f470951332959580424d4bf4f","123456","admin"));

	}
}
