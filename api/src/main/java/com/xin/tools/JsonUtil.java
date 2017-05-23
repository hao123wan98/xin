package com.xin.tools;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xin.tools.common.TPErrorCodeGeneral;

/**
 * 
 */
public class JsonUtil {
	private static final Log log = LogFactory.getLog(JsonUtil.class);
	/**
	 * ObjectMapper 是线程安全的，可以共享，但共享的结果是效率比较低（内部应该还是有竞争冲突）
	 * 使用空间换取时间，为没有引用对象创建一个副本。
	 */
	private static ThreadLocal<ObjectMapper> localMapper = new ThreadLocal<ObjectMapper>();

	private static ObjectMapper getMapper() {
		ObjectMapper mapper = (ObjectMapper) localMapper.get();
		if (mapper == null) {
			mapper = new ObjectMapper();
			localMapper.set(mapper);
		}
		/*
		 * 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化 //Include.Include.ALWAYS 默认
		 * //Include.NON_DEFAULT 属性为默认值不序列化 //Include.NON_EMPTY 属性为 空（""） 或者为
		 * NULL 都不序列化 //Include.NON_NULL 属性为NULL 不序列化
		 */
		// mapper.setSerializationInclusion(Inclusion.NON_NULL);
		mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return mapper;
	}

	public static <T> T toObject(String jsonStr, Class<T> classType) {
		ObjectMapper om = JsonUtil.getMapper();
		T object = null;
		try {
			object = om.readValue(jsonStr, classType);
		} catch (JsonParseException e) {
			log.error(classType.toString() + e.getMessage());
		} catch (JsonMappingException e) {
			log.error(classType.toString() + e.getMessage());
		} catch (IOException e) {
			log.error(classType.toString() + e.getMessage());
		}
		return object;
	}

	public static <T> List<T> toArray(String jsonStr, Class<T> classType) {
		if (jsonStr == null) {
			return null;
		}

		ObjectMapper mapper = JsonUtil.getMapper();
		List<T> list = null;
		try {
			JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, classType);
			list = mapper.readValue(jsonStr, javaType);
		} catch (JsonParseException e) {
			log.error(classType.toString() + e.getMessage());
		} catch (JsonMappingException e) {
			log.error(classType.toString() + e.getMessage());
		} catch (IOException e) {
			log.error(classType.toString() + e.getMessage());
		}
		return list;
	}

	public static String toJson(Object object) {
		ObjectMapper mapper = JsonUtil.getMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			if (log.isErrorEnabled()) {
				log.error("SerializeObject:" + object + " error.", e);
			}
		} catch (JsonMappingException e) {
			if (log.isErrorEnabled()) {
				log.error("SerializeObject:" + object + " error.", e);
			}
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("SerializeObject:" + object + " error.", e);
			}
		}
		return null;
	}

	public static String mapStrTojson(Map<String, ?> map) {
		ObjectMapper mapper = JsonUtil.getMapper();
		JsonFactory f = mapper.getFactory();
		StringWriter w = new StringWriter();
		JsonGenerator g;
		try {
			g = f.createGenerator(w);
			g.writeStartObject();// {
			// 遍历map
			writeTreeMap(g, map);

			g.writeEndObject();
			g.writeEndObject();
			g.close();
			return w.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String resultErrorToJson(String errCode, String errMsg) {
		ObjectMapper mapper = JsonUtil.getMapper();
		JsonFactory f = mapper.getFactory();
		StringWriter w = new StringWriter();
		JsonGenerator g;
		try {
			g = f.createGenerator(w);
			g.writeStartObject();// {
			g.writeStringField("code", errCode);
			if (errMsg != null) {
				g.writeStringField("msg", errMsg);
			}
			g.writeEndObject();
			g.close();
			return w.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * TODO:待测试 允许向ResultObj中加入json串，或者对象
	 * 
	 * @creator 李树涛
	 * @createTime:2015年1月21日 下午7:39:13 ==edit===========
	 * @updator: 李树涛
	 * @updateTime 2015年1月21日 下午7:39:13
	 * @Description: 填写修改内容 ==============
	 * @param jsonParams
	 *            attr
	 * @return
	 */
	public static String resultObjTojson(Object obj) {
		ObjectMapper mapper = JsonUtil.getMapper();
		JsonFactory f = mapper.getFactory();
		StringWriter w = new StringWriter();
		JsonGenerator g;
		try {
			g = f.createGenerator(w);
			g.writeStartObject();// {
			g.writeObjectField("code", TPErrorCodeGeneral.Succeed_Param);
			g.writeStringField("msg", "ok");
			if (obj != null) {
				if (obj instanceof String) {
					g.writeFieldName("data");
					g.writeString((String) obj);
				} else {
					g.writeObjectField("data", obj);
				}
			}

			g.writeEndObject();
			g.close();
			return w.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String resultObjTojson() {
		ObjectMapper mapper = JsonUtil.getMapper();
		JsonFactory f = mapper.getFactory();
		StringWriter w = new StringWriter();
		JsonGenerator g;
		try {
			g = f.createGenerator(w);
			g.writeStartObject();// {
			g.writeObjectField("code", TPErrorCodeGeneral.Succeed_Param);
			g.writeStringField("msg", "ok");
			g.writeEndObject();
			g.close();
			return w.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String resultObjAndStrTojson(String str) {
		ObjectMapper mapper = JsonUtil.getMapper();
		JsonFactory f = mapper.getFactory();
		StringWriter w = new StringWriter();
		JsonGenerator g;
		try {
			g = f.createGenerator(w);
			g.writeStartObject();// {
			g.writeObjectField("code", TPErrorCodeGeneral.Succeed_Param);
			g.writeStringField("msg", "ok");
			if (str != null) {
				g.writeStringField("data", str);
			}
			g.writeEndObject();
			g.close();
			return w.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String resultObjTojson(List<?> list) {
		ObjectMapper mapper = JsonUtil.getMapper();
		JsonFactory f = mapper.getFactory();
		StringWriter w = new StringWriter();
		JsonGenerator g;
		try {
			g = f.createGenerator(w);
			g.writeStartObject();// {
			g.writeObjectField("code", TPErrorCodeGeneral.Succeed_Param);
			g.writeStringField("msg", "ok");
			g.writeArrayFieldStart("data");

			for (Object tem : list) {
				if (tem instanceof Map) {
					g.writeStartObject();// {
					writeTreeMap(g, (Map) tem);
					g.writeEndObject();
				} else if (tem instanceof String) {
					g.writeString((String) tem);
				} else {
					g.writeObject(tem);
				}
			}

			g.writeEndArray();
			g.writeEndObject();
			g.close();
			return w.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String listToJson(List<?> list) {
		ObjectMapper mapper = JsonUtil.getMapper();
		JsonFactory f = mapper.getFactory();
		StringWriter w = new StringWriter();
		JsonGenerator g;
		try {
			g = f.createGenerator(w);
			g.writeStartArray();
			for (Object tem : list) {
				if (tem instanceof Map) {
					g.writeStartObject();// {
					writeTreeMap(g, (Map) tem);
					g.writeEndObject();
				} else if (tem instanceof String) {
					g.writeString((String) tem);
				} else {
					g.writeObject(tem);
				}
			}
			g.writeEndArray();
			g.close();
			return w.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String resultObjTojson(Map<String, ?> map) {
		ObjectMapper mapper = JsonUtil.getMapper();
		JsonFactory f = mapper.getFactory();
		StringWriter w = new StringWriter();
		JsonGenerator g;
		try {
			g = f.createGenerator(w);
			g.writeStartObject();// {
			g.writeObjectField("code", TPErrorCodeGeneral.Succeed_Param);
			g.writeStringField("msg", "ok");
			g.writeObjectFieldStart("data");
			// 遍历map
			writeTreeMap(g, map);

			g.writeEndObject();
			g.writeEndObject();
			g.close();
			return w.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private static void writeTreeMap(JsonGenerator g, Map<String, ?> map) throws JsonGenerationException, IOException {
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object value = map.get(key);
			if (value instanceof String) {
				g.writeFieldName(key);
				g.writeString((String) value);
			} else if (value instanceof Map) {
				Map<String, ?> subMap = (Map<String, ?>) value;
				g.writeObjectFieldStart(key); // key:{
				writeTreeMap(g, subMap);
				g.writeEndObject();// }
			} else {
				g.writeObjectField(key, value);
			}

		}
	}

	public static String resultObjTojson(String key, Object value) {
		ObjectMapper mapper = JsonUtil.getMapper();
		JsonFactory f = mapper.getFactory();
		StringWriter w = new StringWriter();
		JsonGenerator g;
		try {
			g = f.createGenerator(w);
			g.writeStartObject();// {
			g.writeObjectField("code", TPErrorCodeGeneral.Succeed_Param);
			g.writeStringField("msg", "ok");
			g.writeObjectFieldStart("data");
			if (value instanceof String) {
				g.writeFieldName(key);
				g.writeString((String) value);
			} else {
				g.writeObjectField(key, value);
			}
			g.writeEndObject();
			g.writeEndObject();
			g.close();
			return w.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @creator 李树涛
	 * @createTime:2015年2月10日 下午3:58:05 ==edit===========
	 * @updator: 李树涛
	 * @updateTime 2015年2月10日 下午3:58:05
	 * @Description: 填写修改内容 ==============
	 * @param json
	 * @param attr
	 * @param value
	 * @return
	 */
	public static String simpleUpdateAttr(String json, String attr, Object value) {
		ObjectMapper m = JsonUtil.getMapper();
		JsonNode rootNode;
		try {
			rootNode = m.readValue(json, JsonNode.class);
			if (value instanceof String) {
				((ObjectNode) rootNode).put(attr, (String) value);
			} else if (value instanceof Long) {
				((ObjectNode) rootNode).put(attr, (Long) value);
			} else if (value instanceof Integer) {
				((ObjectNode) rootNode).put(attr, (Integer) value);
			} else if (value instanceof Byte) {
				((ObjectNode) rootNode).put(attr, (Byte) value);
			}
			return m.writeValueAsString(rootNode);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String simpleUpdateAttr(String json, Map<String, Object> map) {
		ObjectMapper m = JsonUtil.getMapper();
		JsonNode rootNode;
		try {
			rootNode = m.readValue(json, JsonNode.class);
			Set<Entry<String, Object>> hashSet = map.entrySet();
			for (Entry<String, Object> tem : hashSet) {
				String key = tem.getKey();
				Object value = tem.getValue();
				if (value instanceof String) {
					((ObjectNode) rootNode).put(key, (String) value);
				} else if (value instanceof Long) {
					((ObjectNode) rootNode).put(key, (Long) value);
				} else if (value instanceof Integer) {
					((ObjectNode) rootNode).put(key, (Integer) value);
				} else if (value instanceof Byte) {
					((ObjectNode) rootNode).put(key, (Byte) value);
				}
			}

			return m.writeValueAsString(rootNode);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String simpleRemoveAttr(String json, String... attr) {
		ObjectMapper m = JsonUtil.getMapper();
		JsonNode rootNode;
		try {
			rootNode = m.readValue(json, JsonNode.class);
			for (String temAttr : attr) {
				((ObjectNode) rootNode).remove(temAttr);
			}
			return m.writeValueAsString(rootNode);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 从json中读取tagPath处的值 tagPath用 :分隔
	 * 
	 * @param json
	 * @param tagPath
	 * @return
	 * @throws Exception
	 */
	public static List<String> readValueFromJson(String json, String tagPath) throws Exception {
		// 返回值
		List<String> value = new ArrayList<String>();
		if (CommonUtil.isEmpty(json) || (CommonUtil.isEmpty(tagPath))) {
			return value;
		}
		ObjectMapper mapper = JsonUtil.getMapper();
		String[] path = tagPath.split(":");
		JsonNode node = mapper.readTree(json);
		getJsonValue(node, path, value, 1);
		return value;
	}

	public static void getJsonValue(JsonNode node, String[] path, List<String> values, int nextIndex) {
		if (CommonUtil.isEmpty(node)) {
			return;
		}
		// 是路径的最后就直接取值
		if (nextIndex == path.length) {
			if (node.isArray()) {
				for (int i = 0; i < node.size(); i++) {
					JsonNode child = node.get(i).get(path[nextIndex - 1]);
					if (CommonUtil.isEmpty(child)) {
						continue;
					}
					values.add(child.toString());
				}
			} else {
				JsonNode child = node.get(path[nextIndex - 1]);
				if (!CommonUtil.isEmpty(child)) {
					values.add(child.toString());
				}
			}
			return;
		}
		// 判断是Node下是集合还是一个节点
		node = node.get(path[nextIndex - 1]);
		if (node.isArray()) {
			for (int i = 0; i < node.size(); i++) {
				getJsonValue(node.get(i), path, values, nextIndex + 1);
			}
		} else {
			getJsonValue(node, path, values, nextIndex + 1);
		}
	}

}
