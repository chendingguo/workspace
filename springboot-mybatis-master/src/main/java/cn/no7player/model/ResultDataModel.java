package cn.no7player.model;

import java.util.List;

import lombok.Data;
@Data
public class ResultDataModel<T>{
	int total;
	List<T> rows;

	
}
