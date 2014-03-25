package js;

/**
 * 解析dmzj代码，拼接地址
 * 
 * @author
 * @param var
 *            arr_img = new Array(); var page = ''; eval(function(p, a, c, k, e,
 *            d) { e = function(c) { return (c < a ? '': e(parseInt(c / a))) +
 *            ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36))
 *            }; if (!''.replace(/^/, String)) { while (c--) { d[e(c)] = k[c] ||
 *            e(c) } k = [function(e) { return d[e] }]; e = function() { return
 *            '\\w+' }; c = 1 }; while (c--) { if (k[c]) { p = p.replace(new
 *            RegExp('\\b' + e(c) + '\\b', 'g'), k[c]) } } return p }
 */
public class Evalunzip {
	public String parseMi(int index, int lim) {
		String q1 = (index < lim ? "" : parseMi(index / lim, lim));
		String q2 = (((index = index % lim) > 35) ? ("" + (char) (index + 29))
				: parseJZ(index, 36));
		return q1 + q2;
	}

	public String parseJZ(int d, int jizhi) {
		String[] s = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z"
				.split(",");
		return s[d];
	}

	public String unzipEval(String mi, int lim, int index, String[] key) {
		System.out.println();
		System.out.println("index:" + index);
		System.out.println("key.length:" + key.length);
		index = key.length;
		while ((index--) != 0) {
			if (key[index] != null && !"".equals(key[index])) {
				String s = parseMi(index, lim);
				// System.out.println(s);
				mi = mi.replaceAll("\\b" + s + "\\b", key[index]);
			}
		}
		return mi;
	}

	public static void main(String[] args) {
		String mi = "'z h=h=\'[\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/y.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/x.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/u.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/w.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/A.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/B.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/F.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/E.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/D.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/C.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/G.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/p.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/n.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/l.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/k.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/j.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/m.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/t.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/s.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/r.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/o.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/q.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/i.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/v.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/T.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/Y.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/X.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/W.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/V.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/Z.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/10.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/15.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/H.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/13.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/12.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/11.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/14.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/U.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/M.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/L.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/K.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/I.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/J.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/N.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/O.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/S.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/R.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/Q.6\",\"a\\/%0%7%5%0%1%9%0%2%3%4%8%c\\/%f%g%d%b%e%1\\/P.6\"]\';'";
		String[] key = "E6|9D|95|99|E5|97|jpg|9A|AE|80||E8|A4|AC01|AF|E7|AC|pages|023|016|015|014|017|013|021|012|022|020|019|018|003|024|004|002|001|var|005|006|010|009|008|007|011|033|042|043|041|040|039|044|045|049|048|047|046|025|038|029|028|027|026|030|031|036|035|034|037|032"
				.split("\\|");
		String s = new Evalunzip().unzipEval(mi, 62, 68, key);
		System.out.println(s);
		
		/*
		   加密之前:
		   funciton show() {
    			var i = 9
			} 
			加密之后:
			eval(function(p,a,c,k,e,r){e=String;if('0'.replace(0,e)==0){while(c--)r[e(c)]=k[c];k=[function(e){return r[e]||e}];e=function(){return'^$'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}
			('funciton show(){var i=9}',[],1,''.split('|'),0,{}))
			参数1：p = 'funciton show(){var i=9}' 有用
			参数2：a = []                         有用
			参数3：c = 1                                                                      有用
			参数4：k = ''.split('|')              有用
			参数5：e = 0
			参数6：r = {}
			
		 */
		System.out.println(new Evalunzip().unzipEval("'funciton show(){var i=9}'", 0, 1, "".split("\\|")));
	}
}
