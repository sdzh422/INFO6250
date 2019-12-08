package com.me.tracking.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.type.TrueFalseType;

import com.me.tracking.exception.PackageException;
import com.me.tracking.pojo.PackageInfo;

import java.util.List;



public class PackageDAO extends DAO{
	
	public PackageInfo create(PackageInfo package1) throws PackageException{
		try {
			begin();
			getSession().save(package1);
			commit();
		
		} catch (HibernateException e) {
			rollback();
			throw new PackageException("Exception while creating package: " + e.getMessage());
		}
		return package1;
	}
	
	public PackageInfo getPackage(String number) throws PackageException
	{
		try {
			begin();
			Query q = getSession().createQuery("from PackageInfo p where p.trackingNumber = :trackingNumber");
			q.setString("trackingNumber", number);
			PackageInfo packageInfo = (PackageInfo)q.uniqueResult();
			commit();
			return packageInfo;
		}	
		 catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new PackageException("Could not obtain the  package - " + number + " " + e.getMessage());
		}
	}
	
	public List<PackageInfo> getMyPackage(String phone) throws PackageException
	{
		try {
			begin();
			Query q = getSession().createQuery("from PackageInfo p where p.phone = :phone");
			q.setString("phone", phone);
			List<PackageInfo> packageList = q.list();
			commit();
			return packageList;
		}
		catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new PackageException("Could not obtain the  package List - " + phone + " " + e.getMessage());
		}
	}
	
	public void updatePackage(String trackingnum,String status,String location) throws PackageException
	{
		try {
			begin();
			String hql ="update PackageInfo p set p.status=:status "+",p.location=:location"
					+ "where p.trackingNumber = :trackingNumber";
			Query q = getSession().createQuery(hql);
			q.setString("trackingNumber", trackingnum);
			q.setString("status", status);
			q.setString("location", location);
			q.executeUpdate();
			commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new PackageException("Could not update the package - " + trackingnum + " " + e.getMessage());
		}
	}
	
	

}
