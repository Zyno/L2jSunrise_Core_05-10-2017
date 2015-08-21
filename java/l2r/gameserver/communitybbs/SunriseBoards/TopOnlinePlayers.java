package l2r.gameserver.communitybbs.SunriseBoards;

import gr.sr.configsEngine.configs.impl.SmartCommunityConfigs;
import gr.sr.dataHolder.PlayersTopData;
import gr.sr.datatables.SunriseTable;

/**
 * @author L2jSunrise Team
 * @Website www.l2jsunrise.com
 */
public class TopOnlinePlayers
{
	private final StringBuilder _topOnline = new StringBuilder();
	private int _counter = 0;
	
	public TopOnlinePlayers(String file)
	{
		loadDB(file);
	}
	
	private void loadDB(String file)
	{
		for (PlayersTopData playerData : SunriseTable.getInstance().getTopOnlineTime())
		{
			if (getCounter() <= SmartCommunityConfigs.TOP_PLAYER_RESULTS)
			{
				String name = playerData.getCharName();
				String cName = playerData.getClanName();
				int onlineTime = playerData.getOnlineTime();
				
				addChar(name, cName, getPlayerRunTime(onlineTime));
				setCounter(getCounter() + 1);
			}
		}
	}
	
	public String loadTopList()
	{
		return _topOnline.toString();
	}
	
	private void addChar(String name, String cname, String onTime)
	{
		_topOnline.append("<table border=0 cellspacing=0 cellpadding=2 bgcolor=111111 width=750>");
		_topOnline.append("<tr>");
		_topOnline.append("<td FIXWIDTH=40>" + getCounter() + "</td");
		_topOnline.append("<td fixwidth=160>" + name + "</td");
		_topOnline.append("<td fixwidth=160>" + cname + "</td>");
		_topOnline.append("<td fixwidth=160>" + onTime + "</td>");
		_topOnline.append("</tr>");
		_topOnline.append("</tr></table><img src=\"L2UI.Squaregray\" width=\"735\" height=\"1\">");
	}
	
	public String getPlayerRunTime(int secs)
	{
		String timeResult = "";
		if (secs >= 86400)
		{
			timeResult = Integer.toString(secs / 86400) + " Days " + Integer.toString((secs % 86400) / 3600) + " hours";
		}
		else
		{
			timeResult = Integer.toString(secs / 3600) + " Hours " + Integer.toString((secs % 3600) / 60) + " mins";
		}
		return timeResult;
	}
	
	public int getCounter()
	{
		return _counter;
	}
	
	public void setCounter(int counter)
	{
		_counter = counter;
	}
}