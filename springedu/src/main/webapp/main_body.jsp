<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="container">	
	
  <div class="row mb-2">
  
  <c:if test=${user ne null }>
   <b>Log IN</b>
  </c:if>
  body
	${user.id }
    <div class="col-md-6">
      <div class="card flex-md-row mb-4 shadow-sm h-md-250">
        <div class="card-body d-flex flex-column align-items-start">
          <strong class="d-inline-block mb-2 text-primary">World</strong>
          <h3 class="mb-0">
            <a class="text-dark" href="#">Featured post</a>
          </h3>
          <div class="mb-1 text-muted">Nov 12</div>
          <p class="card-text mb-auto">This is a wider card with supporting text below as a natural lead-in to additional content.</p>
          <a href="#">Continue reading</a>
        </div>
        <svg class="bd-placeholder-img card-img-right flex-auto d-none d-lg-block" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect fill="#55595c" width="100%" height="100%"/><text fill="#eceeef" dy=".3em" x="50%" y="50%">Thumbnail</text></svg>
      </div>
    </div>
    <div class="col-md-6">
      <div class="card flex-md-row mb-4 shadow-sm h-md-250">
        <div class="card-body d-flex flex-column align-items-start">
          <strong class="d-inline-block mb-2 text-success">Design</strong>
          <h3 class="mb-0">
            <a class="text-dark" href="#">Post title</a>
          </h3>
          <div class="mb-1 text-muted">Nov 11</div>
          <p class="card-text mb-auto">This is a wider card with supporting text below as a natural lead-in to additional content.</p>
          <a href="#">Continue reading</a>
        </div>
        <svg class="bd-placeholder-img card-img-right flex-auto d-none d-lg-block" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect fill="#55595c" width="100%" height="100%"/><text fill="#eceeef" dy=".3em" x="50%" y="50%">Thumbnail</text></svg>
      </div>
    </div>
  </div>
  </div>
<main role="main" class="container">
<div class="row">
	<div class="col-md-8 blog-main">
		<h3 class="pb-3 mb-4 font-italic border-bottom">From the Firehose
		</h3>

		<div class="blog-post">
			<h2 class="blog-post-title">Sample blog post</h2>
			<p class="blog-post-meta">
				January 1, 2014 by <a href="#">Mark</a>
			</p>

			<p>This blog post shows a few different types of content that’s
				supported and styled with Bootstrap. Basic typography, images, and
				code are all supported.</p>
			<hr>
			<p>
				Cum sociis natoque penatibus et magnis <a href="#">dis
					parturient montes</a>, nascetur ridiculus mus. Aenean eu leo quam.
				Pellentesque ornare sem lacinia quam venenatis vestibulum. Sed
				posuere consectetur est at lobortis. Cras mattis consectetur purus
				sit amet fermentum.
			</p>
			<blockquote>
				<p>
					Curabitur blandit tempus porttitor. <strong>Nullam quis
						risus eget urna mollis</strong> ornare vel eu leo. Nullam id dolor id nibh
					ultricies vehicula ut id elit.
				</p>
			</blockquote>
			<p>
				Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis
				consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla
				sed consectetur.
			</p>
			<h2>Heading</h2>
			<p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor
				auctor. Duis mollis, est non commodo luctus, nisi erat porttitor
				ligula, eget lacinia odio sem nec elit. Morbi leo risus, porta ac
				consectetur ac, vestibulum at eros.</p>
			<h3>Sub-heading</h3>
			<p>Cum sociis natoque penatibus et magnis dis parturient montes,
				nascetur ridiculus mus.</p>
			<pre>
				<code>Example code block</code>
			</pre>
			<p>Aenean lacinia bibendum nulla sed consectetur. Etiam porta sem
				malesuada magna mollis euismod. Fusce dapibus, tellus ac cursus
				commodo, tortor mauris condimentum nibh, ut fermentum massa.</p>
			<h3>Sub-heading</h3>
			<p>Cum sociis natoque penatibus et magnis dis parturient montes,
				nascetur ridiculus mus. Aenean lacinia bibendum nulla sed
				consectetur. Etiam porta sem malesuada magna mollis euismod. Fusce
				dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
				ut fermentum massa justo sit amet risus.</p>
			<ul>
				<li>Praesent commodo cursus magna, vel scelerisque nisl
					consectetur et.</li>
				<li>Donec id elit non mi porta gravida at eget metus.</li>
				<li>Nulla vitae elit libero, a pharetra augue.</li>
			</ul>
			<p>Donec ullamcorper nulla non metus auctor fringilla. Nulla
				vitae elit libero, a pharetra augue.</p>
			<ol>
				<li>Vestibulum id ligula porta felis euismod semper.</li>
				<li>Cum sociis natoque penatibus et magnis dis parturient
					montes, nascetur ridiculus mus.</li>
				<li>Maecenas sed diam eget risus varius blandit sit amet non
					magna.</li>
			</ol>
			<p>Cras mattis consectetur purus sit amet fermentum. Sed posuere
				consectetur est at lobortis.</p>
		</div>
		<!-- /.blog-post -->

		<div class="blog-post">
			<h2 class="blog-post-title">Another blog post</h2>
			<p class="blog-post-meta">
				December 23, 2013 by <a href="#">Jacob</a>
			</p>

			<p>
				Cum sociis natoque penatibus et magnis <a href="#">dis
					parturient montes</a>, nascetur ridiculus mus. Aenean eu leo quam.
				Pellentesque ornare sem lacinia quam venenatis vestibulum. Sed
				posuere consectetur est at lobortis. Cras mattis consectetur purus
				sit amet fermentum.
			</p>
			<blockquote>
				<p>
					Curabitur blandit tempus porttitor. <strong>Nullam quis
						risus eget urna mollis</strong> ornare vel eu leo. Nullam id dolor id nibh
					ultricies vehicula ut id elit.
				</p>
			</blockquote>
			<p>
				Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis
				consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla
				sed consectetur.
			</p>
			<p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor
				auctor. Duis mollis, est non commodo luctus, nisi erat porttitor
				ligula, eget lacinia odio sem nec elit. Morbi leo risus, porta ac
				consectetur ac, vestibulum at eros.</p>
		</div>
		<!-- /.blog-post -->

		<div class="blog-post">
			<h2 class="blog-post-title">New feature</h2>
			<p class="blog-post-meta">
				December 14, 2013 by <a href="#">Chris</a>
			</p>

			<p>Cum sociis natoque penatibus et magnis dis parturient montes,
				nascetur ridiculus mus. Aenean lacinia bibendum nulla sed
				consectetur. Etiam porta sem malesuada magna mollis euismod. Fusce
				dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
				ut fermentum massa justo sit amet risus.</p>
			<ul>
				<li>Praesent commodo cursus magna, vel scelerisque nisl
					consectetur et.</li>
				<li>Donec id elit non mi porta gravida at eget metus.</li>
				<li>Nulla vitae elit libero, a pharetra augue.</li>
			</ul>
			<p>
				Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis
				consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla
				sed consectetur.
			</p>
			<p>Donec ullamcorper nulla non metus auctor fringilla. Nulla
				vitae elit libero, a pharetra augue.</p>
		</div>
		<!-- /.blog-post -->

		<nav class="blog-pagination">
			<a class="btn btn-outline-primary" href="#">Older</a> <a
				class="btn btn-outline-secondary disabled" href="#">Newer</a>
		</nav>

	</div>
	<!-- /.blog-main -->

	<aside class="col-md-4 blog-sidebar">
		<div class="p-3 mb-3 bg-light rounded">
			<h4 class="font-italic">About</h4>
			<p class="mb-0">
				Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis
				consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla
				sed consectetur.
			</p>
		</div>

		<div class="p-3">
			<h4 class="font-italic">Archives</h4>
			<ol class="list-unstyled mb-0">
				<li><a href="#">March 2014</a></li>
				<li><a href="#">February 2014</a></li>
				<li><a href="#">January 2014</a></li>
				<li><a href="#">December 2013</a></li>
				<li><a href="#">November 2013</a></li>
				<li><a href="#">October 2013</a></li>
				<li><a href="#">September 2013</a></li>
				<li><a href="#">August 2013</a></li>
				<li><a href="#">July 2013</a></li>
				<li><a href="#">June 2013</a></li>
				<li><a href="#">May 2013</a></li>
				<li><a href="#">April 2013</a></li>
			</ol>
		</div>

		<div class="p-3">
			<h4 class="font-italic">Elsewhere</h4>
			<ol class="list-unstyled">
				<li><a href="#">GitHub</a></li>
				<li><a href="#">Twitter</a></li>
				<li><a href="#">Facebook</a></li>
			</ol>
		</div>
	</aside>
	<!-- /.blog-sidebar -->

</div>
<!-- /.row --> </main>
<!-- /.container -->


</body>
 --%>
 
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <main role="main" class="container">
  
<!-- 		<div class="map_thumb" aria-hidden="true" id="headQuertersMap" style="background: url(&quot;https://i1.daumcdn.net/dmaps/apis/loading_n.png&quot;); position: relative; overflow: hidden;">
		<div style="position: absolute; left: 0px; top: 0px; width: 100%; height: 100%; touch-action: none; cursor: url(&quot;https://i1.daumcdn.net/dmaps/apis/cursor/openhand.cur.ico&quot;) 7 5, url(&quot;https://i1.daumcdn.net/dmaps/apis/cursor/openhand.cur.ico&quot;), default;"><div style="position: absolute;"><div style="position: absolute; z-index: 0;"></div>
		<div style="position: absolute; z-index: 1; left: 0px; top: 0px;"> -->
			
		</main><!-- /.container -->