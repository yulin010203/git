package capture;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;

/**
 * @author 陈霖 2014年6月24日
 */
public class Snippet {

	/**
	 * @param ip
	 * @throws Exception
	 */
	public void captureHtml(String ip) throws Exception {
		// String strURL = "http://ip.chinaz.com/?";
		URL url = new URL(ip);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		InputStreamReader input = new InputStreamReader(httpConn.getInputStream(), "utf-8");
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		// File file = new File("test1.html");
		// if (!file.exists()){
		// file.createNewFile();
		// }
		// BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new
		// FileOutputStream(file), "gb2312"));
		// StringBuilder contentBuf = new StringBuilder();
		
		while ((line = bufReader.readLine()) != null) {
			if (line.contains("op-stockdynamic-cur-num c-gap-right-small")){
				String[] arr = line.split("op-stockdynamic-cur-num c-gap-right-small");
				StringTokenizer st = new StringTokenizer(arr[1], "\"><");
//				System.out.println(st.nextToken());
				double d = Double.valueOf(st.nextToken());
				break;
//				System.out.println(line);
			}
			// bw.write(line);
			// bw.write("\n");
			// contentBuf.append(line);
		}
		// bw.flush();
		bufReader.close();
		// bw.close();
		// String buf = contentBuf.toString();
		// int beginIx = buf.indexOf("查询结果[");
		// int endIx = buf.indexOf("上面四项依次显示的是");
		// String result = buf.substring(beginIx, endIx);
		// System.out.println("captureHtml()的结果：\n" + result);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Snippet s = new Snippet();
		// String url =
		// "http://finance.sina.com.cn/realstock/company/sh000300/nc.shtml";
		// String url = "http://quote.eastmoney.com/zs000300.html";
		
		String url = "http://www.baidu.com/s?wd=%E6%B2%AA%E6%B7%B1300%E6%8C%87%E6%95%B0&tn=baidu&ie=utf-8&f=3&rsv_bp=1&inputT=3309&rsv_sug3=15&rsv_sug4=297&rsv_sug1=17&oq=%E6%B2%AA%E6%B7%B1300%20&rsv_sug2=1&rsp=2&bs=%E6%B2%AA%E6%B7%B1300&rsv_spt=3";
		long start = System.nanoTime();
		s.captureHtml(url);
		long end = System.nanoTime();
		System.out.println(end -start);
		start = System.nanoTime();
		s.captureHtml(url);
		end = System.nanoTime();
		System.out.println(end -start);
	}
}
