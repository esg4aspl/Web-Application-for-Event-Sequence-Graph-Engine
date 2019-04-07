function mxSquareEllipse(bounds, fill, stroke, strokewidth){
      mxShape.call(this);
      this.bounds = bounds;
  	  this.fill = fill;
  	  this.stroke = stroke;
  	  this.strokewidth = (strokewidth != null) ? strokewidth : 1;
  	  
};

mxUtils.extend(mxSquareEllipse,mxShape);



mxSquareEllipse.prototype.paintVertexShape = function(c, x, y, w, h)

{

	c.translate(x, y);
	c.begin();
	this.redrawPath(c, x, y, w, h);
	c.fillAndStroke();
};

mxSquareEllipse.prototype.redrawPath = function(c, x, y, w, h)
{
	var width = w/3;
	c.moveTo(w/1.1,h/1.92);
    c.lineTo(w/1.1,h);
    c.moveTo(0,h/2.5);
    c.curveTo(0 ,h/6.5 ,w,h/6.5,w,h/2.5);
    c.moveTo(0,h/2.5);
	c.curveTo(0,h/1.5,w,h/1.5,w,h/2.5);
    c.moveTo(w/1.1,h);
    c.lineTo(w/1.7,h);
    c.moveTo(w/1.7,h);
    c.lineTo(w/1.7,h/1.67);
    
};
