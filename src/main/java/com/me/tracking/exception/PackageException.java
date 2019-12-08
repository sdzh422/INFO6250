package com.me.tracking.exception;

public class PackageException extends Exception{
	public PackageException(String message)
	{
		super("PackageException-"+message);
	}
	
	public PackageException(String message, Throwable cause)
	{
		super("PackageException-"+message,cause);
	}

}
