package com.swj.service;

import org.springframework.dao.DataAccessException;

/**
 * ҵ����Ļ����࣬����ҵ���඼���ɴ���
 * @author fz 2011-12-29
 *
 */
public interface BaseService {
	
	public Object getObjectById(String mapName,String id) throws DataAccessException;
	
	public Object getObjectById(String mapName,int id) throws DataAccessException;
}
