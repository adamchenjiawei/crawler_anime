package anime_chuixue.utils;

import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class CommonUtils {
	public static  String base64_decode(String data){
		String b64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
		int o1, o2, o3, h1, h2, h3, h4, bits;
		int i = 0, ac = 0; 
		String dec = "";
		String[] tmp_arr =  new String[data.length()+1];
		if (StringUtils.isBlank(data)) {
			return data;
		}
		do { 
			h1 = b64.indexOf(data.charAt(i++));
			h2 = b64.indexOf(data.charAt(i++));
			h3 = b64.indexOf(data.charAt(i++));
			h4 = b64.indexOf(data.charAt(i++));
			bits = h1<<18 | h2<<12 | h3<<6 | h4;
			o1 = bits>>16 & 0xff;
			o2 = bits>>8 & 0xff;
			o3 = bits & 0xff;
			if (h3 == 64) {
				tmp_arr[ac++] = (char)o1 + "";
			} else if (h4 == 64) {
				String name = (char)o1 + "";
				name += (char)o2 + "";

				tmp_arr[ac++] = name;
			} else {
				String name = (char)o1 + "";
				name += (char)o2 +"";
				name += (char)o3 +"";
				tmp_arr[ac++] = name;
			}
		} while (i < data.length());
		String ret = "";
		for (String str : tmp_arr) {
			if(StringUtils.isBlank(str)){
				break;
			}
			ret += str;
		}
		dec = ret;
		System.out.println(dec);
		return dec;
	}
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
		String base64_decode = CommonUtils.base64_decode("aHR0cDovL2NhcnRvb24ueW91enU4OC5jb20vbWFuaHVhdHVrdS8yMTE2NS9tYW5odWFfMTJfMjAxNTExMDhfMjAxNTExMDgwODAzMzI5MjQ0MS5qcGckcWluZ3RpYW5keSRodHRwOi8vY2FydG9vbi55b3V6dTg4LmNvbS9tYW5odWF0dWt1LzIxMTY1L21hbmh1YV8xMl8yMDE1MTEwOF8yMDE1MTEwODA4MDMzNTMwMDE5LmpwZyRxaW5ndGlhbmR5JGh0dHA6Ly9jYXJ0b29uLnlvdXp1ODguY29tL21hbmh1YXR1a3UvMjExNjUvbWFuaHVhXzEyXzIwMTUxMTA4XzIwMTUxMTA4MDgwMzM5NDIxNjIuanBnJHFpbmd0aWFuZHkkaHR0cDovL2NhcnRvb24ueW91enU4OC5jb20vbWFuaHVhdHVrdS8yMTE2NS9tYW5odWFfMTJfMjAxNTExMDhfMjAxNTExMDgwODAzNDIxOTkyOC5qcGckcWluZ3RpYW5keSRodHRwOi8vY2FydG9vbi55b3V6dTg4LmNvbS9tYW5odWF0dWt1LzIxMTY1L21hbmh1YV8xMl8yMDE1MTEwOF8yMDE1MTEwODA4MDM0NzM2MzEyLmpwZyRxaW5ndGlhbmR5JGh0dHA6Ly9jYXJ0b29uLnlvdXp1ODguY29tL21hbmh1YXR1a3UvMjExNjUvbWFuaHVhXzEyXzIwMTUxMTA4XzIwMTUxMTA4MDgwMzUxMTUwNjcuanBnJHFpbmd0aWFuZHkkaHR0cDovL2NhcnRvb24ueW91enU4OC5jb20vbWFuaHVhdHVrdS8yNTIwNS8yMDE2LTEyLTMwLzIwMTYxMjMwMDkxMjI1OTliMjllLmpwZyRxaW5ndGlhbmR5JGh0dHA6Ly9jYXJ0b29uLnlvdXp1ODguY29tL21hbmh1YXR1a3UvMjExNjUvbWFuaHVhXzEyXzIwMTUxMTA4XzIwMTUxMTA4MDgwMzUzODI5NzYuanBnJHFpbmd0aWFuZHkkaHR0cDovL2NhcnRvb24ueW91enU4OC5jb20vbWFuaHVhdHVrdS8yMTE2NS9tYW5odWFfMTJfMjAxNTExMDhfMjAxNTExMDgwODAzNTU4NjgwMi5qcGckcWluZ3RpYW5keSRodHRwOi8vY2FydG9vbi55b3V6dTg4LmNvbS9tYW5odWF0dWt1LzIxMTY1L21hbmh1YV8xMl8yMDE1MTEwOF8yMDE1MTEwODA4MDM1NjQxMTc5LmpwZyRxaW5ndGlhbmR5JGh0dHA6Ly9jYXJ0b29uLnlvdXp1ODguY29tL21hbmh1YXR1a3UvMjExNjUvbWFuaHVhXzEyXzIwMTUxMTA4XzIwMTUxMTA4MDgwMzU4Nzg2ODkuanBnJHFpbmd0aWFuZHkkaHR0cDovL2NhcnRvb24ueW91enU4OC5jb20vbWFuaHVhdHVrdS8yMTE2NS9tYW5odWFfMTJfMjAxNTExMDhfMjAxNTExMDgwODA0MDA0ODM4MC5qcGckcWluZ3RpYW5keSRodHRwOi8vY2FydG9vbi55b3V6dTg4LmNvbS9tYW5odWF0dWt1LzIxMTY1L21hbmh1YV8xMl8yMDE1MTEwOF8yMDE1MTEwODA4MDQwMjMzOTY5LmpwZyRxaW5ndGlhbmR5JGh0dHA6Ly9jYXJ0b29uLnlvdXp1ODguY29tL21hbmh1YXR1a3UvMjExNjUvbWFuaHVhXzEyXzIwMTUxMTA4XzIwMTUxMTA4MDgwNDAzNDc5NzkuanBnJHFpbmd0aWFuZHkkaHR0cDovL2NhcnRvb24ueW91enU4OC5jb20vbWFuaHVhdHVrdS8yMTE2NS9tYW5odWFfMTJfMjAxNTExMDhfMjAxNTExMDgwODA0MDU4MzA3Mi5qcGckcWluZ3RpYW5keSRodHRwOi8vY2FydG9vbi55b3V6dTg4LmNvbS9tYW5odWF0dWt1LzIxMTY1L21hbmh1YV8xMl8yMDE1MTEwOF8yMDE1MTEwODA4MDQwNzM2ODEyLmpwZyRxaW5ndGlhbmR5JGh0dHA6Ly9jYXJ0b29uLnlvdXp1ODguY29tL21hbmh1YXR1a3UvMjExNjUvbWFuaHVhXzEyXzIwMTUxMTA4XzIwMTUxMTA4MDgwNDA5NjExMTYuanBnJHFpbmd0aWFuZHkkaHR0cDovL2NhcnRvb24ueW91enU4OC5jb20vbWFuaHVhdHVrdS8yMTE2NS9tYW5odWFfMTJfMjAxNTExMDhfMjAxNTExMDgwODA0MTE0MjY1MS5qcGckcWluZ3RpYW5keSRodHRwOi8vY2FydG9vbi55b3V6dTg4LmNvbS9tYW5odWF0dWt1LzIxMTY1L21hbmh1YV8xMl8yMDE1MTEwOF8yMDE1MTEwODA4MDQxMzI1NDA1LmpwZyRxaW5ndGlhbmR5JGh0dHA6Ly9jYXJ0b29uLnlvdXp1ODguY29tL21hbmh1YXR1a3UvMjExNjUvbWFuaHVhXzEyXzIwMTUxMTA4XzIwMTUxMTA4MDgwNDE1MTE3MDguanBnJHFpbmd0aWFuZHkkaHR0cDovL2NhcnRvb24ueW91enU4OC5jb20vbWFuaHVhdHVrdS8yMTE2NS9tYW5odWFfMTJfMjAxNTExMDhfMjAxNTExMDgwODA0MTc1MjkzMS5qcGckcWluZ3RpYW5keSRodHRwOi8vY2FydG9vbi55b3V6dTg4LmNvbS9tYW5odWF0dWt1LzIxMTY1L21hbmh1YV8xMl8yMDE1MTEwOF8yMDE1MTEwODA4MDQxODU2MTMxLmpwZyRxaW5ndGlhbmR5JGh0dHA6Ly9jYXJ0b29uLnlvdXp1ODguY29tL21hbmh1YXR1a3UvMjExNjUvbWFuaHVhXzEyXzIwMTUxMTA4XzIwMTUxMTA4MDgwNDIwMzc4NzUuanBnJHFpbmd0aWFuZHkkaHR0cDovL2NhcnRvb24ueW91enU4OC5jb20vbWFuaHVhdHVrdS8yMTE2NS9tYW5odWFfMTJfMjAxNTExMDhfMjAxNTExMDgwODA0MjI3MTUwNi5qcGckcWluZ3RpYW5keSRodHRwOi8vY2FydG9vbi55b3V6dTg4LmNvbS9tYW5odWF0dWt1LzIxMTY1L21hbmh1YV8xMl8yMDE1MTEwOF8yMDE1MTEwODA4MDQyMzI5MTAyLmpwZyRxaW5ndGlhbmR5JGh0dHA6Ly9jYXJ0b29uLnlvdXp1ODguY29tL21hbmh1YXR1a3UvMjExNjUvbWFuaHVhXzEyXzIwMTUxMTA4XzIwMTUxMTA4MDgwNDI1NzQzNTcuanBnJHFpbmd0aWFuZHkkaHR0cDovL2NhcnRvb24ueW91enU4OC5jb20vbWFuaHVhdHVrdS8yMTE2NS9tYW5odWFfMTJfMjAxNTExMDhfMjAxNTExMDgwODA0MjYyNzY2OC5qcGckcWluZ3RpYW5keSRodHRwOi8vY2FydG9vbi55b3V6dTg4LmNvbS9tYW5odWF0dWt1LzIxMTY1L21hbmh1YV8xMl8yMDE1MTEwOF8yMDE1MTEwODA4MDQyODg5NjQ1LmpwZyRxaW5ndGlhbmR5JGh0dHA6Ly9jYXJ0b29uLnlvdXp1ODguY29tL21hbmh1YXR1a3UvMjExNjUvbWFuaHVhXzEyXzIwMTUxMTA4XzIwMTUxMTA4MDgwNDMwNTQzMTYuanBnJHFpbmd0aWFuZHkkaHR0cDovL2NhcnRvb24ueW91enU4OC5jb20vbWFuaHVhdHVrdS8yMTE2NS9tYW5odWFfMTJfMjAxNTExMDhfMjAxNTExMDgwODA0MzEzODI2Ny5qcGckcWluZ3RpYW5keSRodHRwOi8vY2FydG9vbi55b3V6dTg4LmNvbS9tYW5odWF0dWt1LzIxMTY1L21hbmh1YV8xMl8yMDE1MTEwOF8yMDE1MTEwODA4MDQzMzM2OTc0LmpwZyRxaW5ndGlhbmR5JGh0dHA6Ly9jYXJ0b29uLnlvdXp1ODguY29tL21hbmh1YXR1a3UvMjExNjUvbWFuaHVhXzEyXzIwMTUxMTA4XzIwMTUxMTA4MDgwNDM1NzQ1NjEuanBnJHFpbmd0aWFuZHkkaHR0cDovL2NhcnRvb24ueW91enU4OC5jb20vbWFuaHVhdHVrdS8yMTE2NS9tYW5odWFfMTJfMjAxNTExMDhfMjAxNTExMDgwODA0MzY1ODI2MC5qcGc=");
		System.out.println("decode:"+base64_decode);
		System.out.println("length:"+base64_decode.length());
		String[] list = base64_decode.split("\\$qingtiandy\\$");
		for(int i = 0; i < list.length; i++){
			System.out.println(list[i]);
		}
		
		String string = UUID.randomUUID().toString();
		String replace = string.replace("-", "");
		System.out.println(replace+"   "+replace.length() );
		
	}
}

