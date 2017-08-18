/**
 * 存储localStorage
 */
export const setStore = (name, content) => {
	if (!name) return;
	if (typeof content !== 'string') {
		content = JSON.stringify(content);
	}
	window.localStorage.setItem(name, content);
}

/**
 * 获取localStorage
 */
export const getStore = name => {
	if (!name) return;
	return window.localStorage.getItem(name);
}

/**
 * 删除localStorage
 */
export const removeStore = name => {
	if (!name) return;
	window.localStorage.removeItem(name);
}


export const getUrlKey = name => {
	return decodeURIComponent((new RegExp('[?|&]' + name + '=' +
		'([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(
		/\+/g, '%20')) || null;
}
