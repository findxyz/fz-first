// insertAdjacentHTML(), insertAdjacentText() and insertAdjacentElement()
// for Netscape 6/Mozilla by Thor Larholm me@jscript.dk
// Usage: include this code segment at the beginning of your document
// before any other Javascript contents.

if(typeof HTMLElement!="undefined" && !HTMLElement.prototype.insertAdjacentElement){
	HTMLElement.prototype.insertAdjacentElement = function(where,parsedNode)
	{		
		switch (where){
			case 'beforeBegin':
				this.parentNode.insertBefore(parsedNode,this)
				break;
			case 'afterBegin':
				this.insertBefore(parsedNode,this.firstChild);
				break;
			case 'beforeEnd':				
				this.appendChild(parsedNode);
				break;
			case 'afterEnd':
				if (this.nextSibling) 
					this.parentNode.insertBefore(parsedNode,this.nextSibling);
				else this.parentNode.appendChild(parsedNode);
				break;
		}
	}

	HTMLElement.prototype.insertAdjacentHTML = function(where,htmlStr)
	{
		var r = this.ownerDocument.createRange();
		r.setStartBefore(this);
		var parsedHTML = r.createContextualFragment(htmlStr);
		this.insertAdjacentElement(where,parsedHTML)
	}


	HTMLElement.prototype.insertAdjacentText = function(where,txtStr)
	{
		var parsedText = document.createTextNode(txtStr)
		this.insertAdjacentElement(where,parsedText)
	}
}

/**=============================================���ܽ���==================================================
* ���ܣ�����֤����ӡ�ĵ�������
* ������certSize    
*				���飬֤����С[width,height]����λcm(1cm=37.795px)�����磺[19.5,19.5]��
*       certData    
*				�ַ�����֤�����ݣ��Զ��ŷָ������磺"'����','�Ա�','����'"��
*       certDataPos 
*				�ַ�����֤�����ݵ�λ��[top,left]����λpx�����磺"[['180px','100px'],['200px','100px']]"��
*       certBackImg 
*				�ַ�����֤������ͼ��Ե�ַ�����磺"img/marry.jpg"��
*=======================================================================================================**/
/**=============================================�޸���ʷ====================================================
* version  changelog                                                           date
* ==========================================================================================================
* 0.3.0    �޸ĺ���drawCert(certSize,certBackImg,certData,certDataPos)��       2006-12-22   
*          ����ΪdrawCert(certSize,certData,certDataPos,certBackImg)��
*          ���������б���Ҳ����û�б���
*=========================================================================================================**/
function drawCert(certSize,certData,certDataPos,certBackImg){
	//��������������width��height
	backWidth=certSize[0]*37.795+'px';
	backHeight=certSize[1]*37.795+'px';	
	
	//֤������	
	if(certData.charAt(0)!='['&& certData.charAt(certData.length-1)!=']')
		certData = eval("["+certData+"]");
	else
		certData = eval(certData);
	
	//֤������λ��
	certDataPos = eval(certDataPos);	
	
	//����certPanel
	var certPanelDiv=document.createElement("div");
	
	certPanelDiv.id="certPanel"
	certPanelDiv.className="cert";
	
	certPanelDiv.style.width=backWidth;
	certPanelDiv.style.height=backHeight;	
	
	scrollPanel.insertAdjacentElement("beforeEnd",certPanelDiv);	
	
	//��certPanel�ϻ��Ʊ���	
	if(certBackImg)
		certPanelDiv.insertAdjacentHTML("beforeEnd","<img id='backImg' src='"+certBackImg+"' width='"+backWidth+"' height='"+backHeight+"'/>");	
	
	//��certPanel�ϻ�������	
	for(i=0;i<certData.length;i++){
		var certDataSpan=document.createElement("span")
				
		certDataSpan.name="certData";
		certDataSpan.className="data";
		
		//������ݺ�λ�ó��Ȳ�һ�²���Ĭ��ֵ
		if(i<certDataPos.length){
			certDataSpan.style.top=certDataPos[i][0];
			certDataSpan.style.left=certDataPos[i][1];
		}else{
			certDataSpan.style.top=20*i+"px";
			certDataSpan.style.left=20+"px";
		}
		
		//IE֧��innerText��FireFox��NetScape֧��textContent��֧��innerText
		if(navigator.appName.indexOf("Explorer") > -1){
			certDataSpan.innerText=certData[i];
		} else{
			certDataSpan.textContent=certData[i];
		}		
		
		//ʹ�����ܹ��϶�
		makeDraggable(certDataSpan);
		
		certPanelDiv.insertAdjacentElement("beforeEnd",certDataSpan)
	}
}

//�洢scrollPanel��͸ߵ���ʱ����
var scrollPanelWidth;
var scrollPanelHeight;

//��ӡǰִ�ж���
function bodyOnBeforePrint() {
	//�洢scrollPanel��͸�
	scrollPanelWidth = scrollPanel.style.width;
	scrollPanelHeight = scrollPanel.style.height;
	
	//����scrollPanel
	scrollPanel.style.width = certPanel.style.width ;
	scrollPanel.style.height = certPanel.style.height;
	scrollPanel.style.overflow="hidden";
	
	//����certPanel
	certPanel.style.top=0;
	certPanel.style.left=0;
	certPanel.style.borderTopWidth=0;
	certPanel.style.borderLeftWidth=0;
	certPanel.style.borderRightWidth=0;
	certPanel.style.borderBottomWidth=0;
	
	//����backImg
	backImg.style.display ="none"
}

//��ӡ��ִ�ж���
function bodyOnAfterPrint() {
	//��ʾscrollPanel
	scrollPanel.style.width = scrollPanelWidth ;
	scrollPanel.style.height = scrollPanelHeight;
	scrollPanel.style.overflow = "scroll";	
	
	//��ʾcertPanel
	certPanel.style.top=0
	certPanel.style.left=0
	certPanel.style.borderTopWidth=2
	certPanel.style.borderLeftWidth=2
	certPanel.style.borderRightWidth=2
	certPanel.style.borderBottomWidth=2
	
	//��ʾbackImg
	backImg.style.display ="block"
}

//�ռ����ݵ�λ��
function collectDataPos() {
	var posArray = [];
	//���ҳ���ϵ�spanԪ�أ�
	//getElementsByTagName��ie��mozilla��netscape��ͨ��
	var dataList=document.getElementsByTagName("span");
	for(var i=0;i<dataList.length;i++){
		//����֤��Ԫ��
		if(dataList[i].name=="certData"){			
			posArray[posArray.length]=[dataList[i].style.top,dataList[i].style.left];
		}
	}
		
	return posArray.toJSONString();
}

//���һ������Ԫ
//ע�⣺��Ҫ��ҳ��������һ��<input type="text" id="dataDetail" value="">
function addDataObj(){
	var dataValueObj = document.getElementById("dataDetail");
	
	if(dataValueObj&&dataValueObj.value!=""){
		var certDataSpan=document.createElement("span")
					
		certDataSpan.name="certData";
		certDataSpan.className="data";
		
		//Ĭ��λ��		
		certDataSpan.style.top=20+"px";
		certDataSpan.style.left=20+"px";
		
		
		//IE֧��innerText��FireFox��NetScape֧��textContent��֧��innerText
		if(navigator.appName.indexOf("Explorer") > -1){
			certDataSpan.innerText=dataValueObj.value;
		} else{
			certDataSpan.textContent=dataValueObj.value;
		}		
		
		//ʹ�����ܹ��϶�
		makeDraggable(certDataSpan);
		
		//֤������
		var certPanelDiv=document.getElementById("certPanel");
		if(certPanelDiv)		
			certPanelDiv.insertAdjacentElement("beforeEnd",certDataSpan)
			
		dataValueObj.value = "";
	}
}
