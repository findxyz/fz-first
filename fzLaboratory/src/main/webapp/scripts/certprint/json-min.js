Array.prototype.toJSONString=function(){function a(c){f&&d.push(",");d.push(c);f=!0}var d=["["],f,b,c=this.length,e;for(b=0;b<c;b+=1)switch(e=this[b],typeof e){case "undefined":case "function":case "unknown":break;case "object":e?typeof e.toJSONString==="function"&&a(e.toJSONString()):a("null");break;default:a(e.toJSONString())}d.push("]");return d.join("")};Boolean.prototype.toJSONString=function(){return String(this)};
Date.prototype.toJSONString=function(){function a(a){return a<10?"0"+a:a}return'"'+this.getFullYear()+"-"+a(this.getMonth()+1)+"-"+a(this.getDate())+"T"+a(this.getHours())+":"+a(this.getMinutes())+":"+a(this.getSeconds())+'"'};Number.prototype.toJSONString=function(){return isFinite(this)?String(this):"null"};
Object.prototype.toJSONString=function(){function a(a){f&&d.push(",");d.push(b.toJSONString(),":",a);f=!0}var d=["{"],f,b,c;for(b in this)if(this.hasOwnProperty(b))switch(c=this[b],typeof c){case "undefined":case "function":case "unknown":break;case "object":c?typeof c.toJSONString==="function"&&a(c.toJSONString()):a("null");break;default:a(c.toJSONString())}d.push("}");return d.join("")};
(function(a){var d={"\u0008":"\\b","\t":"\\t","\n":"\\n","\u000c":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"};a.parseJSON=function(a){try{if(/^("(\\.|[^"\\\n\r])*?"|[,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t])+?$/.test(this)){var b=eval("("+this+")");if(typeof a==="function"){var c=function(b){if(b&&typeof b==="object")for(var d in b)b.hasOwnProperty(d)&&(b[d]=c(b[d]));return a(b)};return c(b)}return b}}catch(d){}throw new SyntaxError("parseJSON");};a.toJSONString=function(){if(/["\\\x00-\x1f]/.test(this))return'"'+
this.replace(/([\x00-\x1f\\"])/g,function(a,b){var c=d[b];if(c)return c;c=b.charCodeAt();return"\\u00"+Math.floor(c/16).toString(16)+(c%16).toString(16)})+'"';return'"'+this+'"'}})(String.prototype);
