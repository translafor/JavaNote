package testresp;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
public class MenuInfor {
//	public MenuInfor(String name, String url) {
//		this.name=name;
//		this.url=url;
//	}

//	Integer urlType;
//	Integer menuLevel;
	List<MenuInforItem> kingKong;
	List<MenuInforItem> moreService;

	public static void main(String[] args) {
		MenuInforItem setting = MenuInforItem.builder().name("设置").url("/setting").sort(1).build();
		MenuInforItem setting2 = MenuInforItem.builder().name("设置").url("/setting").sort(1).build();
		MenuInforItem setting3 = MenuInforItem.builder().name("设置").url("/setting").sort(1).build();
		MenuInforItem setting4 = MenuInforItem.builder().name("设置").url("/setting").sort(1).build();
//		setting.getChildMenus().add(safe);
//
//		MenuInfor wallet = MenuInfor.builder().name("我的钱包").childMenus(new ArrayList<>()).url("/wallet").menuLevel(1).urlType(1).build();

		List<MenuInforItem> menuInfors = new ArrayList<>();
		menuInfors.add(setting);
		menuInfors.add(setting2);


		List<MenuInforItem> menuInfors2 = new ArrayList<>();
		menuInfors2.add(setting3);
		menuInfors2.add(setting4);

		System.out.println(JSON.toJSONString(MenuInfor.builder().kingKong(menuInfors).moreService(menuInfors2).build()));
	}

	@Data
	@Builder
	@AllArgsConstructor
	public static class  MenuInforItem{
		Integer menuCode;
		String name="";
		String url="";
		Integer sort;
	}
}
